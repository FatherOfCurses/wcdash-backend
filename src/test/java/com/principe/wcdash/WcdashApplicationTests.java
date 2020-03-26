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
        public void ICanRetrieveASingleMinimalTransactionFromDatabase() {
                Transaction expectedMinimal = TestHelper.createMinimalTrans(1);
                databaseService.writeMinimalTransToDatabase(expectedMinimal);
                Transaction actualMinimal = new Transaction();
                actualMinimal = databaseService.getMinimalTrans("5856E79D-88F4-428B-B328-004136CE750F1");
                assertEquals(expectedMinimal, actualMinimal);
        }

        @Test
        public void ICanRetrieveAllMinimalTransactionsFromDatabase() {
                ArrayList<Transaction> expectedMinimalArray = new ArrayList<>();
                ArrayList<Transaction> actualMinimalArray = new ArrayList<>();
                for(int i = 0 ; i < 5; i ++) {
                        Transaction thisMinimal = TestHelper.createMinimalTrans(i);
                        databaseService.writeMinimalTransToDatabase(thisMinimal);
                        expectedMinimalArray.add(thisMinimal);
                }
                for(int j = 0 ; j < 5; j++) {
                        actualMinimalArray.add(databaseService.getMinimalTrans("5856E79D-88F4-428B-B328-004136CE750F" + j));
                }
                assertEquals(expectedMinimalArray, actualMinimalArray);
        }
}

