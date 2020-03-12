package com.principe.wcdash.daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.principe.wcdash.domain.MinimalTrans;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class CompletionDao extends AbstractDao {

    public static String TRANSACTION_COLLECTION = "trans";
    private MongoCollection<Document> completionCollection;

    @Autowired
    public CompletionDao(
            MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        completionCollection = db.getCollection(TRANSACTION_COLLECTION);
    }

}
