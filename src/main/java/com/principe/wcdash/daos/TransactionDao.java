package com.principe.wcdash.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.principe.wcdash.domain.FullTransDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<FullTransDetails, String>{
}
