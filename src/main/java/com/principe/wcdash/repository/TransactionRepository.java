package com.principe.wcdash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.principe.wcdash.domain.FullTransDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<FullTransDetails, String>{
}
