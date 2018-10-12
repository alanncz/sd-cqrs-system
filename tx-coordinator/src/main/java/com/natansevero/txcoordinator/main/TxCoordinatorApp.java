/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.txcoordinator.main;

import com.natansevero.shared.services.TxDatabaseService;
import com.natansevero.txcoordinator.impl.TxCoordServiceImpl;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import com.natansevero.shared.services.TxCoordService;

/**
 *
 * @author natan
 */
public class TxCoordinatorApp {
    
    public static TxDatabaseService getPostgresObj() throws RemoteException, NotBoundException {
//        Registry registry = LocateRegistry.getRegistry(1515);
        Registry registry = LocateRegistry.getRegistry("postgresapp-link", 1515);        
        return (TxDatabaseService) registry.lookup("DatabaseService");
    }
    
    public static TxDatabaseService getMongoDbObj() throws RemoteException, NotBoundException {
//        Registry registry = LocateRegistry.getRegistry(2020);
        Registry registry = LocateRegistry.getRegistry("mongodbapp-link", 2020);
        return (TxDatabaseService) registry.lookup("DatabaseService");
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        List<TxDatabaseService> txDatabases = new ArrayList<>();
        txDatabases.add(getPostgresObj());
        txDatabases.add(getMongoDbObj());
        
        TxCoordService obj = new TxCoordServiceImpl(txDatabases);
        TxCoordService stub = (TxCoordService) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(3131);
        registry.bind("TxCoordService", stub);
    }
}
