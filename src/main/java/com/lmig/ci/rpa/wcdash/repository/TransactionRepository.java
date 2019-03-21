package com.lmig.ci.rpa.wcdash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lmig.ci.rpa.wcdash.domain.FullTransDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<FullTransDetails, String>{
}
