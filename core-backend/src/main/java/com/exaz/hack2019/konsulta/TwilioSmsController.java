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

@Controller
public class TwilioSmsController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    @RequestMapping(path = "/hooks/sms", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processHook(@RequestBody String kvpInput) {
        log.info(kvpInput);
        return new ResponseEntity<>("You said something I don't understand, 'cause I'm still a basic bot at the moment." , HttpStatus.OK);
    }
}
