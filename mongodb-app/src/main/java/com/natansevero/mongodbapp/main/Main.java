/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.mongodbapp.main;

import com.natansevero.mongodbapp.impl.DatabaseServiceImpl;
import com.natansevero.shared.services.DatabaseService;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author natan
 */
public class Main {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        DatabaseService databaseService = new DatabaseServiceImpl();
        DatabaseService stub = (DatabaseService) UnicastRemoteObject.exportObject(databaseService, 0);
        Registry registry = LocateRegistry.createRegistry(2020);
        registry.bind("DatabaseService", stub);
    }
}
