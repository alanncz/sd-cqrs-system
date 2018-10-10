/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.mongodbapp.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author natan
 */
public class Connection {
    private final MongoClient client;
    private final MongoDatabase database;    
    private final CodecRegistry codecRegistry;
    
    public Connection() {
        codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), 
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       
//        client = new MongoClient("localhost",
//                MongoClientOptions.builder().codecRegistry(codecRegistry).build()
//        );

        client = new MongoClient("banco-mongodb-app",
                MongoClientOptions.builder().codecRegistry(codecRegistry).build()
        );
        
        database = client.getDatabase("cqrssystem").withCodecRegistry(codecRegistry);
    }
    
    public MongoCollection getCollection(String name, Class classe) {
        return database.getCollection(name, classe);
    }
}
