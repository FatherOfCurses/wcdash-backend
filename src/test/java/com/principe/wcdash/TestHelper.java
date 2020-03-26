package com.principe.wcdash;

import com.principe.wcdash.domain.Transaction;

import java.time.LocalDateTime;

public class TestHelper {

        public static Transaction createMinimalTrans(int transIndex) {
                Transaction newMinimal = new Transaction();
                newMinimal.setTransIdFromDB("5856E79D-88F4-428B-B328-004136CE750F" + transIndex);
                newMinimal.setClaimnumber("WC116-A52881");
                newMinimal.setStatustext("Completed");
                newMinimal.setTransactionCompleteTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                newMinimal.setExceptionReason2("exceptionreason2");
                newMinimal.setWorkTimeInSecs(513);
                newMinimal.setXmldata("stupidxmltext");
                return newMinimal;
        }
}
