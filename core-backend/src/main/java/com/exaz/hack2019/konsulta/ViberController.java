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
public class ViberController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    @RequestMapping(path = "/hooks/viber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> processHook(@RequestBody String jsonInput) {
        log.info("\n" + jsonInput);
        return new ResponseEntity<>(Collections.singletonMap("ack", "ok"), HttpStatus.OK);
    }

}
