package com.principe.wcdash.daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.principe.wcdash.domain.Transaction;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;

@Component("completionDao")
public class CompletionDao extends AbstractDao {

    @Value("${spring.mongodb.database}")
    //public static String TRANSACTION_COLLECTION;
    public static String TRANSACTION_COLLECTION = "transaction";
    private MongoCollection<Document> completionCollection;

    @Autowired
    public CompletionDao(
            MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        completionCollection = db.getCollection(TRANSACTION_COLLECTION);
    }

    public Transaction findById(String transIdFromDB) {
        List<Bson> findByIdFilter = Arrays.asList(match(eq("transId", transIdFromDB)));
        Document transactionDocument = completionCollection.aggregate(findByIdFilter).first();
        if (!(transactionDocument == null)) {
            return docToTransMapper(transactionDocument);
        } else {
            return null;
        }
    }

    public List<Transaction> findAll() {
        List<Document> transactionDocuments = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        completionCollection
                .find()
                .iterator()
                .forEachRemaining(transactionDocuments::add);
        for (Document thisTransaction : transactionDocuments)
            transactions.add(docToTransMapper(thisTransaction));
        return transactions;
    }

    public void save(Transaction writeTrans) {
        Document saveDocument = transToDocMapper(writeTrans);
        completionCollection.insertOne(saveDocument);
    }

    private Transaction docToTransMapper(Document transFromDB) {
        Transaction transaction = new Transaction();
        transaction.setTransIdFromDB(transFromDB.get("_id").toString());
        transaction.setClaimnumber(transFromDB.get("claimNumber").toString());
        transaction.setStatustext(transFromDB.get("statusText").toString());
        transaction.setTransactionCompleteTime((LocalDateTime) transFromDB.get("transactionCompleteTime"));
        transaction.setExceptionReason2(transFromDB.get("exceptionReason2").toString());
        transaction.setWorkTimeInSecs(transFromDB.get("workTimeInSecs").hashCode());
        return transaction;
    }

    private Document transToDocMapper(Transaction transaction) {
        Document transDocument = new Document();
        return transDocument;
    }

}
