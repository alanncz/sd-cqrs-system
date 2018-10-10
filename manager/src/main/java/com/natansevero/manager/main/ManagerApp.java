/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.manager.main;

import com.natansevero.manager.services.ManagerServiceImpl;
import com.natansevero.shared.services.DatabaseService;
import com.natansevero.shared.services.ManagerService;
import com.natansevero.shared.services.TxCoordService;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alann
 */
public class ManagerApp {
    
    public static DatabaseService getPostgresObj() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1515);
        return (DatabaseService) registry.lookup("DatabaseService");
    }
    
    public static DatabaseService getMongoDbObj() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        return (DatabaseService) registry.lookup("DatabaseService");
    }
    
    public static TxCoordService getCoord() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(3131);
        return (TxCoordService) registry.lookup("TxCoordService");
    }
    
   public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        List<DatabaseService> databases = new ArrayList<>();
        databases.add(getPostgresObj());
        databases.add(getMongoDbObj());
        TxCoordService txCoord = getCoord();       
        
        ManagerService obj = new ManagerServiceImpl(txCoord, databases);
        
        ManagerService stub = (ManagerService) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(3030);
        registry.bind("ManagerService", stub);
    }
    
}
