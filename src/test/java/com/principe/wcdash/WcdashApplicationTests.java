package com.principe.wcdash;

import com.principe.wcdash.domain.Transaction;
import com.principe.wcdash.service.DatabaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WcdashApplicationTests {

        @Autowired
        DatabaseServiceImpl databaseService;

        //TODO: More test coverage on transaction retrieval

        @Test
        public void ICanRetrieveASingleTransactionFromDatabase() {
                Transaction trans = TestHelper.createTrans(1);
                    databaseService.writeTransToDatabase(trans);
                Transaction actualTrans = new Transaction();
                actualTrans = databaseService.getTrans("5856E79D-88F4-428B-B328-004136CE750F1");
                assertEquals(trans, actualTrans);
        }

        @Test
        public void ICanRetrieveAllTransactionsFromDatabase() {
                ArrayList<Transaction> expectedTransArray = new ArrayList<>();
                ArrayList<Transaction> actualTransArray = new ArrayList<>();
                for(int i = 0 ; i < 5; i ++) {
                        Transaction thisTrans = TestHelper.createTrans(i);
                        databaseService.writeTransToDatabase(thisTrans);
                        expectedTransArray.add(thisTrans);
                }
                for(int j = 0 ; j < 5; j++) {
                        actualTransArray.add(databaseService.getTrans("5856E79D-88F4-428B-B328-004136CE750F" + j));
                }
                assertEquals(expectedTransArray, actualTransArray);
        }
}

