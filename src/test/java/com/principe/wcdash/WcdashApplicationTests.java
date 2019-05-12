package com.principe.wcdash;

import com.principe.wcdash.domain.FullTransDetails;
import com.principe.wcdash.domain.MinimalTrans;
import com.principe.wcdash.service.DatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WcdashApplicationTests {

        @Autowired
        private DatabaseService databaseService;

        //TODO: More test coverage on transaction retrieval
        @Test
        @Transactional
        public void ICanRetrieveASingleTransactionFromDatabase() {
                FullTransDetails expectedTrans = TestHelper.createTrans(1);
                databaseService.writeTransToDatabase(expectedTrans);
                FullTransDetails actualTrans = new FullTransDetails();
                actualTrans = databaseService.getTrans("5856E79D-88F4-428B-B328-004136CE750F1");
                assertEquals(expectedTrans, actualTrans);
        }

        @Test
        @Transactional
        public void ICanRetrieveAllTransactionsFromDatabase() {
                ArrayList<FullTransDetails> expectedTransArray = new ArrayList<>();
                ArrayList<FullTransDetails> actualTransArray = new ArrayList<>();
                for(int i = 0 ; i < 5; i ++) {
                        FullTransDetails thisTrans = TestHelper.createTrans(i);
                        databaseService.writeTransToDatabase(thisTrans);
                        expectedTransArray.add(thisTrans);
                }
                for(int j = 0 ; j < 5; j++) {
                        actualTransArray.add(databaseService.getTrans("5856E79D-88F4-428B-B328-004136CE750F" + j));
                }
                assertEquals(expectedTransArray, actualTransArray);
        }
        @Test
        @Transactional
        public void ICanRetrieveASingleMinimalTransactionFromDatabase() {
                MinimalTrans expectedMinimal = TestHelper.createMinimalTrans(1);
                databaseService.writeMinimalTransToDatabase(expectedMinimal);
                MinimalTrans actualMinimal = new MinimalTrans();
                actualMinimal = databaseService.getMinimalTrans("5856E79D-88F4-428B-B328-004136CE750F1");
                assertEquals(expectedMinimal, actualMinimal);
        }

        @Test
        @Transactional
        public void ICanRetrieveAllMinimalTransactionsFromDatabase() {
                ArrayList<MinimalTrans> expectedMinimalArray = new ArrayList<>();
                ArrayList<MinimalTrans> actualMinimalArray = new ArrayList<>();
                for(int i = 0 ; i < 5; i ++) {
                        MinimalTrans thisMinimal = TestHelper.createMinimalTrans(i);
                        databaseService.writeMinimalTransToDatabase(thisMinimal);
                        expectedMinimalArray.add(thisMinimal);
                }
                for(int j = 0 ; j < 5; j++) {
                        actualMinimalArray.add(databaseService.getMinimalTrans("5856E79D-88F4-428B-B328-004136CE750F" + j));
                }
                assertEquals(expectedMinimalArray, actualMinimalArray);
        }
}

