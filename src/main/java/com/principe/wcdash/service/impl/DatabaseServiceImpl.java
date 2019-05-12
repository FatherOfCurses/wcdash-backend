package com.principe.wcdash.service.impl;

import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.domain.FullTransDetails;
import com.principe.wcdash.repository.CompletionRepository;
import com.principe.wcdash.repository.TransactionRepository;
import com.principe.wcdash.service.DatabaseService;
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
                return transactionRepository.findById(transIdFromDB).orElse(new FullTransDetails());
        }

        @Override
                public List<FullTransDetails> listAllTrans() {
                return transactionRepository.findAll();
        }

        @Override public void writeTransToDatabase(FullTransDetails writeTrans) {
                transactionRepository.save(writeTrans);
        }

        @Override public MinimalTrans getMinimalTrans(String transIdFromDB) {
                return completionRepository.findById(transIdFromDB).orElse(new MinimalTrans());
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


