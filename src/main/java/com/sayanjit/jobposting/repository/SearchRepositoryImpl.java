package com.sayanjit.jobposting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sayanjit.jobposting.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();
    
        MongoDatabase database = client.getDatabase("telusko");
        MongoCollection<Document> collection = database.getCollection("JobPost");
    
        // Perform a text search using $text
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
            new Document("$match", 
                new Document("$text", new Document("$search", text))
            ),
            new Document("$sort", new Document("exp", 1L)),
            new Document("$limit", 5L)
        ));
    
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
    
        return posts;
    }
    
}
