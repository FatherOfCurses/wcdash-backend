package com.principe.wcdash;

import com.principe.wcdash.domain.FullTransDetails;
import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class TestHelper {

        @Autowired
        private DatabaseService databaseService;

        public static FullTransDetails createTrans(int transIndex) {
                FullTransDetails fullTransDetails = new FullTransDetails();
                fullTransDetails.setTransIdFromDB("5856E79D-88F4-428B-B328-004136CE750F" + transIndex);
                fullTransDetails.setQueueid("088FE57F-FE28-4014-B5C6-ABDBE3A51383");
                fullTransDetails.setClaimnumber("WC116-A52881");
                fullTransDetails.setStatustext("Completed");
                fullTransDetails.setAttempt(1);
                fullTransDetails.setTransactionLoadTime(LocalDateTime.parse("2018-06-26T11:04:58.690"));
                fullTransDetails.setTransactionCompleteTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                fullTransDetails.setTransactionExceptionTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                fullTransDetails.setExceptionreason("exceptionreason");
                fullTransDetails.setTransactionDeferTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                fullTransDetails.setWorkTimeInSecs(513);
                fullTransDetails.setXmldata("stupidxmltext");
                fullTransDetails.setQueueident(29400);
                fullTransDetails.setIdent("1");
                fullTransDetails.setSessionId("CB8EF759-9F1B-41C7-8E2F-3851BB10BFA8");
                fullTransDetails.setPriority(0);
                fullTransDetails.setPreviousWorkTimeInSecs(0);
                fullTransDetails.setAttemptedWorkTimeInSecs(513);
                fullTransDetails.setTransactionFinishTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                fullTransDetails.setExceptionReason2("exceptionreason2");
                fullTransDetails.setExceptionTag("exceptiontag");
                fullTransDetails.setEncryptionId("encryptionid");
                fullTransDetails.setLastTransactionUpdateTime(LocalDateTime.parse("2018-06-26T11:13:31.690"));
                return fullTransDetails;
        }

        public static MinimalTrans createMinimalTrans(int transIndex) {
                MinimalTrans newMinimal = new MinimalTrans();
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
