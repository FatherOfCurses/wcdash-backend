package com.principe.wcdash.service;

import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.domain.FullTransDetails;

import java.util.List;

public interface DatabaseService {

        FullTransDetails getTrans(String transIdFromDB);
        List<FullTransDetails> listAllTrans();
        void writeTransToDatabase(FullTransDetails writeTrans);

        MinimalTrans getMinimalTrans(String transIdFromDB);
        List<MinimalTrans> listAllMinimalTrans();
        void writeMinimalTransToDatabase(MinimalTrans writeMinimal);
}
