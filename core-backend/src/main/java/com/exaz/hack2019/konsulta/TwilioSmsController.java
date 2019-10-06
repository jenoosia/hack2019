package com.exaz.hack2019.konsulta;

import com.exaz.hack2019.konsulta.model.TwilioSms;
import com.exaz.hack2019.konsulta.service.KonsultaService;
import com.twilio.rest.api.v2010.account.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TwilioSmsController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    @Autowired
    private KonsultaService ks;

    //TODO Generalise into a common class
    @SuppressWarnings("Duplicates")
    @RequestMapping(path = "/hooks/sms", method = RequestMethod.POST,
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
