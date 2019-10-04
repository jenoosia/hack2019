package com.exaz.hack2019.konsulta;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

@Controller
public class WhatsappController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    /*
        Send as text/plain or application/xml (TwiML). See:
        - https://www.twilio.com/console/debugger/NO733a2c8bf561701eafc7ec21540946b2
        - https://www.twilio.com/docs/sms/twiml
     */

    @RequestMapping(path = "/hooks/whatsapp", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processHook(@RequestBody String kvpInput) {
        log.info(kvpInput);
        return new ResponseEntity<>("You said something I don't understand, 'cause I'm still a basic bot at the moment." , HttpStatus.OK);
    }

}
