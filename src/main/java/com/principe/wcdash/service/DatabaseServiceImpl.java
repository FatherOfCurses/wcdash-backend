package com.principe.wcdash.service;

import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.daos.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    TransactionDao transactionDao;

    public DatabaseServiceImpl() {
        System.out.println("Hey I impled");
    }

    @Override
    public Transaction getTrans(String transIdFromDB) {
        return transactionDao.findById(transIdFromDB);
    }

    @Override
    public List<Transaction> listAllTrans() {
        transactionDao.findAll();
        List<Transaction> scrubbedList = (transactionDao.findAll()).stream()
                .filter(x -> x.getStatustext() != "")
                .collect(Collectors.toList());
        return scrubbedList;
    }

    @Override
    public void writeTransToDatabase(Transaction writeMinimal) {
        transactionDao.save(writeMinimal);
    }

}


