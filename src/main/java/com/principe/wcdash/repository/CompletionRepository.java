package com.principe.wcdash.repository;

import com.principe.wcdash.domain.MinimalTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletionRepository extends JpaRepository<MinimalTrans, String> {
}
