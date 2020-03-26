package com.principe.wcdash.service;

import com.principe.wcdash.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DatabaseService {

        Transaction getMinimalTrans(String transIdFromDB);
        List<Transaction> listAllMinimalTrans();
        void writeMinimalTransToDatabase(Transaction writeMinimal);
}
