package com.exaz.hack2019.konsulta;

import com.exaz.hack2019.konsulta.model.ViberSend;
import com.exaz.hack2019.konsulta.model.ViberSender;
import com.exaz.hack2019.konsulta.model.ViberWebhookEvent;
import com.exaz.hack2019.konsulta.service.KonsultaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Controller
public class ViberController {

    private final Logger log = LogManager.getLogger(getClass().getName());

    @Autowired
    private KonsultaService ks;

    @RequestMapping(path = "/hooks/viber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> processHook(@RequestBody ViberWebhookEvent vwe) {
        log.info("Received Viber: " + vwe.toString());
        if ("message".equals(vwe.getEvent())) {
            ks.processViberMessage(vwe);
        }

        return new ResponseEntity<>(Collections.singletonMap("ack", "ok"), HttpStatus.OK);
    }

}
