/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.alannapp.resource;

import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.ManagerService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alann
 */

@Path("/alannapp")
public class UserResource {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void newUser(Usuario user) throws SQLException, ClassNotFoundException, RemoteException, NotBoundException {
//        Registry registry = LocateRegistry.getRegistry(3030);
        Registry registry = LocateRegistry.getRegistry("manager-link", 3030);        
        ManagerService stub = (ManagerService) registry.lookup("ManagerService");
        System.out.println(stub.inserir(user));
    }

    
    
    @GET
    public String removerTodos() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("manager-link", 3030);        
        ManagerService stub = (ManagerService) registry.lookup("ManagerService");
        stub.removerTodos();
        return "removeu todos";
    }

}
