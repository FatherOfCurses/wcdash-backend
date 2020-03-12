package com.principe.wcdash.service;

import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.domain.FullTransDetails;
import com.principe.wcdash.daos.CompletionDao;
import com.principe.wcdash.daos.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseServiceImpl implements DatabaseService {

        @Autowired
        TransactionDao transactionDao;

        @Autowired
        CompletionDao completionDao;

        // TODO: Error handling for Database calls?
        @Override
                public FullTransDetails getTrans(String transIdFromDB) {
                return transactionDao.findById(transIdFromDB).orElse(new FullTransDetails());
        }

        @Override
                public List<FullTransDetails> listAllTrans() {
                return transactionDao.findAll();
        }

        @Override public void writeTransToDatabase(FullTransDetails writeTrans) {
                transactionDao.save(writeTrans);
        }

        @Override public MinimalTrans getMinimalTrans(String transIdFromDB) {
                return completionDao.findById(transIdFromDB).orElse(new MinimalTrans());
        }

        @Override public List<MinimalTrans> listAllMinimalTrans() {
                completionDao.findAll();
                List<MinimalTrans> scrubbedList = (completionDao.findAll()).stream()
                        .filter(x -> x.getStatustext()!="")
                        .collect(Collectors.toList());
                return scrubbedList;
        }

        @Override public void writeMinimalTransToDatabase(MinimalTrans writeMinimal) {
                completionDao.save(writeMinimal);
        }

}


