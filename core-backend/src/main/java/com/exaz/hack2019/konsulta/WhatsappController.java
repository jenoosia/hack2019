package com.exaz.hack2019.konsulta;

import com.exaz.hack2019.konsulta.model.TwilioSms;
import com.exaz.hack2019.konsulta.service.KonsultaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
public class WhatsappController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    @Autowired
    private KonsultaService ks;

    /*
        Send as text/plain or application/xml (TwiML). See:
        - https://www.twilio.com/console/debugger/NO733a2c8bf561701eafc7ec21540946b2
        - https://www.twilio.com/docs/sms/twiml
     */

    @Value("${twilio.sid}")
    private String twilioSid;
    @Value("${twilio.token}")
    private String twilioToken;

    //TODO This should just be done once.
    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioSid, twilioToken);
    }

    @RequestMapping(path = "/hooks/whatsapp", method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processHook(@RequestParam Map<String, String> msgMap) {
        final TwilioSms msg = new TwilioSms(msgMap);

        //Only handle received messages
        if (Message.Status.RECEIVED.toString().equals(msg.getSmsStatus())) {
            log.info("Received a new message!");
            log.info(msg.toString());

            final String resp = ks.processTwilioSms(msg);

            return new ResponseEntity<>(resp, HttpStatus.OK);
        }

        return new ResponseEntity<>("" , HttpStatus.OK);
    }

}
