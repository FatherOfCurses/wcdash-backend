package com.lmig.ci.rpa.wcdash.repository;

import com.lmig.ci.rpa.wcdash.domain.MinimalTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO: How to best handle transactions as far as Complete versus Exception - just have one repository and bring back everything, or have two?
@Repository
public interface CompletionRepository extends JpaRepository<MinimalTrans, String>{
}
