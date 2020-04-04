package com.principe.wcdash;

import com.principe.wcdash.domain.Transaction;

import java.time.LocalDateTime;

public class TestHelper {

        public static Transaction createTrans(int transIndex) {
                Transaction trans = new Transaction();
                trans.setTransIdFromDB("5856E79D-88F4-428B-B328-004136CE750F" + transIndex);
                trans.setClaimnumber("WC116-A52881");
                trans.setStatustext("Completed");
                trans.setTransactionCompleteTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                trans.setExceptionReason2("exceptionreason2");
                trans.setWorkTimeInSecs(513);
                trans.setXmldata("stupidxmltext");
                return trans;
        }
}
