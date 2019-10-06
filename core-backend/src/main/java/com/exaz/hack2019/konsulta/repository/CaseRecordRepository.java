package com.exaz.hack2019.konsulta.repository;

import com.exaz.hack2019.konsulta.datamodel.CaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRecordRepository extends JpaRepository<CaseRecord, Integer> {

    CaseRecord findByPrimaryMobile(String mobile);

    CaseRecord findByViberId(String viberId);

    CaseRecord findByCaseRefNum(String caseRefNum);

}
