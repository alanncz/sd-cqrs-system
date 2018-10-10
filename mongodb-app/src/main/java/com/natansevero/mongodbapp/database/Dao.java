/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.mongodbapp.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.natansevero.shared.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natan
 */
public class Dao {
    private final MongoCollection collection;
    private static Dao instance;
    
    private Dao() {
        collection = new Connection().getCollection("usuarios", Usuario.class);
    }
    
    public static Dao getInstance() {
        if(instance == null) {
            instance = new Dao();
            return instance;
        }
        
        return instance;
    }
    
    public void insert(Usuario usuario){
        collection.insertOne(usuario);
    }
    
    public List<Usuario> listAll() {
        List<Usuario> usuarios = new ArrayList<>();
        MongoCursor cursor = collection.find().iterator();
//        
        while(cursor.hasNext()) {
            usuarios.add((Usuario) cursor.next());
        }
        
        return usuarios;
    }
}
