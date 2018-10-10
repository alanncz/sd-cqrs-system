/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.txcoordinator.main;

import com.natansevero.shared.services.TxCoorService;
import com.natansevero.shared.services.TxCoordinatorService;
import com.natansevero.txcoordinator.impl.TxCoorServiceImpl;
import java.rmi.AccessException;
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
 * @author natan
 */
public class Main {
    
    public static TxCoordinatorService getPostgresObj() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1515);
        return (TxCoordinatorService) registry.lookup("DatabaseService");
    }
    
    public static TxCoordinatorService getMongoDbObj() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(2020);
        return (TxCoordinatorService) registry.lookup("DatabaseService");
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        List<TxCoordinatorService> txCoordinators = new ArrayList<>();
        txCoordinators.add(getPostgresObj());
        txCoordinators.add(getMongoDbObj());
        
        TxCoorService obj = new TxCoorServiceImpl(txCoordinators);
        TxCoorService stub = (TxCoorService) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(3131);
        registry.bind("TxCoordinatorService", stub);
    }
}
