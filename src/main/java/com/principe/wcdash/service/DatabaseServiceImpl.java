package com.principe.wcdash.service;

import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.daos.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    TransactionDao completionDao;

    @Override
    public Transaction getTrans(String transIdFromDB) {
        return completionDao.findById(transIdFromDB);
    }

    @Override
    public List<Transaction> listAllTrans() {
        completionDao.findAll();
        List<Transaction> scrubbedList = (completionDao.findAll()).stream()
                .filter(x -> x.getStatustext() != "")
                .collect(Collectors.toList());
        return scrubbedList;
    }

    @Override
    public void writeTransToDatabase(Transaction writeMinimal) {
        completionDao.save(writeMinimal);
    }

}


