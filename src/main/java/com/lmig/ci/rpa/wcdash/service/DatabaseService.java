package com.lmig.ci.rpa.wcdash.service;

import com.lmig.ci.rpa.wcdash.domain.MinimalTrans;
import com.lmig.ci.rpa.wcdash.domain.FullTransDetails;

import java.util.List;

public interface DatabaseService {

        FullTransDetails getTrans(String transIdFromDB);
        List<FullTransDetails> listAllTrans();
        void writeTransToDatabase(FullTransDetails writeTrans);

        MinimalTrans getMinimalTrans(String transIdFromDB);
        List<MinimalTrans> listAllMinimalTrans();
        void writeMinimalTransToDatabase(MinimalTrans writeMinimal);
}
