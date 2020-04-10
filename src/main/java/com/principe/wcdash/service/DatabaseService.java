package com.principe.wcdash.service;

import com.principe.wcdash.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DatabaseService {

        Transaction getTrans(String transIdFromDB);
        List<Transaction> listAllTrans();
        void writeTransToDatabase(Transaction writeMinimal);
}
