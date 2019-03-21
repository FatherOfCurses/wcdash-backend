package com.lmig.ci.rpa.wcdash.service.impl;

import com.lmig.ci.rpa.wcdash.domain.MinimalTrans;
import com.lmig.ci.rpa.wcdash.domain.FullTransDetails;
import com.lmig.ci.rpa.wcdash.repository.CompletionRepository;
import com.lmig.ci.rpa.wcdash.repository.TransactionRepository;
import com.lmig.ci.rpa.wcdash.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseServiceImpl implements DatabaseService {

        @Autowired
        TransactionRepository transactionRepository;

        @Autowired
        CompletionRepository completionRepository;

        // TODO: Error handling for Database calls?
        @Override
                public FullTransDetails getTrans(String transIdFromDB) {
                return transactionRepository.findOne(transIdFromDB);
        }

        @Override
                public List<FullTransDetails> listAllTrans() {
                return transactionRepository.findAll();
        }

        @Override public void writeTransToDatabase(FullTransDetails writeTrans) {
                transactionRepository.save(writeTrans);
        }
        //TODO: Why did I organize this project like this?
        @Override public MinimalTrans getMinimalTrans(String transIdFromDB) {
                return completionRepository.findOne(transIdFromDB);
        }

        @Override public List<MinimalTrans> listAllMinimalTrans() {
                completionRepository.findAll();
                List<MinimalTrans> scrubbedList = (completionRepository.findAll()).stream()
                        .filter(x -> x.getStatustext()!="")
                        .collect(Collectors.toList());
                return scrubbedList;
        }

        @Override public void writeMinimalTransToDatabase(MinimalTrans writeMinimal) {
                completionRepository.save(writeMinimal);
        }

}


