/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.natanapp.resource;

import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.DatabaseService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author natan
 */
public class UsuarioResource extends ServerResource {
    
    @Get("json")
    public Representation getAllPersons() throws RemoteException, NotBoundException {
        JSONArray response = new JSONArray();
        
        Registry registry = LocateRegistry.getRegistry(2020);
        DatabaseService stub = (DatabaseService) registry.lookup("DatabaseService");
        List<Usuario> usuarios = stub.buscarTodos();
        
        try {
            for(Usuario u: usuarios) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uuid", u.getUuid());
                jsonObject.put("nome", u.getNome());
                jsonObject.put("imageUrl", u.getImageUrl());
                
                response.put(jsonObject);
            }
        } catch (JSONException ex) {
            Logger.getLogger(UsuarioResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new JsonRepresentation(response);
    }
}
