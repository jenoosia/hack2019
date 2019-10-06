package com.exaz.hack2019.konsulta.service;

import com.exaz.hack2019.konsulta.constant.CaseRecordStatus;
import com.exaz.hack2019.konsulta.constant.MessageChannels;
import com.exaz.hack2019.konsulta.constant.MessageTypes;
import com.exaz.hack2019.konsulta.datamodel.CaseRecord;
import com.exaz.hack2019.konsulta.datamodel.TheMessage;
import com.exaz.hack2019.konsulta.model.*;
import com.exaz.hack2019.konsulta.repository.CaseRecordRepository;
import com.exaz.hack2019.konsulta.repository.TheMessageRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class KonsultaService {

    private final Logger log = LogManager.getLogger(getClass().getName());

    public static final String FROM_STAFF = "from_staff";


    @Value("${twilio.number.wa}")
    private String twilioNumberWa;
    @Value("${twilio.number.sms}")
    private String twilioNumberSms;

    @Value("${viber.token}")
    private String vt;

    @Autowired
    private RestTemplateBuilder rtb;

    @Autowired
    private CaseRecordRepository crr;
    @Autowired
    private TheMessageRepository tmr;

    //Assume message received
    @Transactional
    public String processViberMessage(ViberWebhookEvent event) {
        //TODO Does not support new sign-ups
        final CaseRecord caseRecord = crr.findByViberId(event.getSender().getId());
        final MessageChannels channel = MessageChannels.Viber;

        caseRecord.setPrimaryChannel(channel.name());

        final VWESender sender = event.getSender();
        final VWEMessage message = event.getMessage();

        //Store the message
        final TheMessage tm = new TheMessage();
        tm.setCaseRecord(caseRecord);
        tm.setCaseRefNum(caseRecord.getCaseRefNum());
        tm.setChannel(channel.name());
        tm.setChatName(caseRecord.getName());
        tm.setCreatedDate(new Date());
        tm.setFromSid(sender.getId());
        tm.setFromUser(sender.getName());
        tm.setAvatar("");
        tm.setMessage(message.getText());
        tm.setMessageType(MessageTypes.Text.name());
        tm.setMsgSid(event.getMessage_token());
        tm.setRaw(event.toString());
        tm.setToUser("Konsulta-Viber");

        tmr.save(tm);

        //TODO Testing

        return "";
    }

    @Transactional
    public String processTwilioSms(TwilioSms sms) {
        final MessageChannels channel = sms.isWa() ? MessageChannels.WhatsApp : MessageChannels.Sms;
        log.info("Processing Twilio message. Message channel: " + channel);
        final String mobile = MessageChannels.Sms.equals(channel) ? sms.getFrom() : sms.getFrom().replace("whatsapp:", "");

        boolean isNewCase = false;

        //Process related CaseRecord
        CaseRecord caseRecord = crr.findByPrimaryMobile(mobile);
        if (caseRecord == null) {
            log.info("No case record found. A new record will be created for this message.");
            caseRecord = newCaseRecord(channel, "User-" + RandomStringUtils.randomAlphanumeric(4), mobile, null);
            isNewCase = true;
        }

        if (StringUtils.isEmpty(caseRecord.getPrimaryMobile())) {
            caseRecord.setPrimaryMobile(mobile);
        }
        caseRecord.setPrimaryChannel(channel.name());

        //Store the message
        final TheMessage tm = new TheMessage();
        tm.setCaseRecord(caseRecord);
        tm.setCaseRefNum(caseRecord.getCaseRefNum());
        tm.setChannel(channel.name());
        tm.setChatName(caseRecord.getName());
        tm.setCreatedDate(new Date());
        tm.setFromSid(sms.getAccountSid());
        tm.setFromUser(sms.getFrom());
        tm.setAvatar("");
        tm.setMessage(sms.getBody());
        tm.setMessageType(sms.getMessageType());
        tm.setMsgSid(sms.getMessageSid());
        tm.setRaw(sms.toString());
        tm.setToUser(sms.getTo());

        tmr.save(tm);

        final String body = sms.getBody();

        if (StringUtils.isBlank(body)) {
            log.info("Nothing of significance was sent.");
            return "";
        }

//        if (body.startsWith("My name is ")) {
//            final String theName = body.substring(body.indexOf("My name is ") + 11);
//            caseRecord.setName(theName);
//            crr.save(caseRecord);
//            tm.setChatName(theName);
//            tmr.save(tm);
//
//            return "Hello, " + theName + "! Welcome to Konsulta. Tell us what you need. :)";
//        }
//
//        if (body.contains("dengue")) {
//
//        }
//
//        if (isNewCase) {
//            return "Welcome to Konsulta, " + caseRecord.getName() + "! We hope you'll find this useful. First, tell us what your name is.";
//        }

//        return "Hang on, we'll ask someone to chat with you soon!";
        return "";
    }

    @Transactional
    public CaseRecord newCaseRecord(final MessageChannels channel, final String name, final String primaryMobile, final String viberId) {
        final CaseRecord cr = new CaseRecord();
        final Date createdDate = new Date();
        cr.setChannels(channel.name());
        cr.setCreatedDate(createdDate);
        cr.setCaseRefNum("CS-" + new SimpleDateFormat("yyMMdd").format(createdDate) + "-" + RandomStringUtils.randomAlphanumeric(4).toUpperCase());
        cr.setName(name);
        cr.setPrimaryChannel(channel.name());
        cr.setPrimaryMobile(primaryMobile);
        cr.setViberId(viberId);
        cr.setStatus(CaseRecordStatus.Active.name());

        crr.save(cr);

        return cr;
    }

    @Transactional
    public List<CaseRecord> allCaseRecords() {
        return this.crr.findAll();
    }

    @Transactional
    public CaseRecord caseRecordByRefNum(final String caseRefNum, final boolean includeMessages) {
        final CaseRecord cr = this.crr.findByCaseRefNum(caseRefNum);
        if (cr == null) {
            return null;
        }

        if (includeMessages) {
            final List<TheMessage> msgs = cr.getMessages();
            if (!msgs.isEmpty()) {
                msgs.get(0);
            }
        }

        return cr;
    }

    @Transactional
    public boolean sendMessage(SendMessageVm sm) {
        final CaseRecord cr = this.crr.findByCaseRefNum(sm.getCaseRefNum());
        if (cr == null) {
            return false; //TODO must work together
        }

        final String channel = cr.getPrimaryChannel();
        if (MessageChannels.WhatsApp.name().equals(channel) || MessageChannels.Sms.name().equals(channel)) {
            final String numPrefix = channel.equals(MessageChannels.WhatsApp.name()) ? "whatsapp:" : "";
            final Message twiMsg = Message.creator(
                    new PhoneNumber(numPrefix + cr.getPrimaryMobile()),
                    new PhoneNumber(numPrefix + (MessageChannels.WhatsApp.name().equals(channel) ? twilioNumberWa : twilioNumberSms)),
                    "From " + sm.getStaffName() + ": " + sm.getMessage()).create();
            log.info(twiMsg);
        } else if (MessageChannels.Viber.name().equals(channel)) {

            final RestTemplate restViber = rtb.build();

            final ViberSend viberSend = new ViberSend();
            viberSend.setReceiver(cr.getViberId());
            viberSend.setText("From " + sm.getStaffName() + ": " + sm.getMessage());
            viberSend.setSender(new ViberSender("Konsulta"));

            final HttpHeaders headers = new HttpHeaders();
            headers.add("X-Viber-Auth-Token", vt);
            HttpEntity<ViberSend> hev = new HttpEntity<>(viberSend, headers);

            final ResponseEntity<String> rvResponse = restViber.exchange("https://chatapi.viber.com/pa/send_message", HttpMethod.POST, hev, String.class);
            log.info("Sent! " + rvResponse.getBody());
        } else {
            log.warn("NA channel!");
        }

        final TheMessage tm = new TheMessage();
        tm.setCaseRecord(cr);
        tm.setCaseRefNum(cr.getCaseRefNum());
        tm.setChannel(channel);
        tm.setChatName(sm.getStaffName());
        tm.setCreatedDate(new Date());
        tm.setFromSid("");
        tm.setFromUser(sm.getStaffName());
        tm.setAvatar(FROM_STAFF);
        tm.setMessage(sm.getMessage());
        tm.setMessageType(MessageTypes.Text.name());
        tm.setMsgSid("");
        tm.setRaw("");
        tm.setToUser(cr.getPrimaryMobile());

        tmr.save(tm);

        return true;
    }
}
