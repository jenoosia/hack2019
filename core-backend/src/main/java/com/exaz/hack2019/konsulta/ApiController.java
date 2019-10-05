package com.exaz.hack2019.konsulta;

import com.exaz.hack2019.konsulta.datamodel.CaseRecord;
import com.exaz.hack2019.konsulta.model.ApiResult;
import com.exaz.hack2019.konsulta.model.CaseVm;
import com.exaz.hack2019.konsulta.model.MessageVm;
import com.exaz.hack2019.konsulta.model.SendMessageVm;
import com.exaz.hack2019.konsulta.service.KonsultaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApiController {

    final Logger log = LogManager.getLogger(getClass().getName());

    @Autowired
    private KonsultaService ks;

    @RequestMapping(path = "/konsulta/cases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CaseVm>> konsultaCases() {
        log.info("Entered konsultaCases.");
        final List<CaseVm> cases = new ArrayList<>();

        ks.allCaseRecords().forEach(cr -> {
            final CaseVm cvm = new CaseVm();
            cvm.setCaseRefNum(cr.getCaseRefNum());
            cases.add(cvm);
        });

        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

    @RequestMapping(path = "/konsulta/case/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CaseVm> konsultaCaseMessages(@RequestBody CaseVm reqCase) {
        log.info("Entered konsultaCaseMessages.");
        final String caseRefNum = reqCase.getCaseRefNum();

        final CaseRecord cr = ks.caseRecordByRefNum(caseRefNum,false);
        if (cr == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final CaseVm respCase = new CaseVm();
        respCase.setCaseRefNum(caseRefNum);
        respCase.setId(Integer.toString(cr.getId()));
        respCase.setName(cr.getName());
        respCase.setAddress(StringUtils.isEmpty(cr.getRemarks()) ? "No known addresses" : cr.getRemarks());
        final String pm = cr.getPrimaryMobile();
        respCase.setMobileNum(pm.startsWith("whatsapp:") ? pm.substring(9) : pm);

        final List<MessageVm> mvms = new ArrayList<>();

        cr.getMessages().forEach(tm -> {
            final MessageVm mvm = new MessageVm();

            mvm.setChannel(tm.getChannel());

            final Date cd = tm.getCreatedDate();
            final boolean sameDay = DateUtils.isSameDay(cd, new Date());
            mvm.setDateSent(new SimpleDateFormat(sameDay ? "h:mm a '| Today'" : "HH:mm:ss | MMMM dd").format(cd));

            mvm.setFromPatient(!KonsultaService.FROM_STAFF.equals(tm.getAvatar()));
            mvm.setId(Integer.toString(tm.getId()));
            mvm.setMessage(tm.getMessage());

            mvms.add(mvm);
        });
        respCase.setMessages(mvms);

        return new ResponseEntity<>(respCase, HttpStatus.OK);
    }

    @RequestMapping(path = "/konsulta/case/message/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResult> konsultaSendMessage(@RequestBody SendMessageVm sendMessage) {
        log.info("Entered konsultaSendMessage.");
        final ApiResult apiResult = new ApiResult();

        final boolean sent = ks.sendMessage(sendMessage);
        apiResult.setSuccess(sent);

        return new ResponseEntity<>(apiResult, HttpStatus.OK);
    }
}
