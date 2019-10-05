package com.exaz.hack2019.konsulta.repository;

import com.exaz.hack2019.konsulta.datamodel.TheMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheMessageRepository extends JpaRepository<TheMessage, Integer> {
}
