/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.postgresapp.main;

import com.natansevero.postgresapp.jdbc.DatabaseServiceImpl;
import com.natansevero.shared.services.DatabaseService;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author alann
 */
public class PostgresApp {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        DatabaseService obj = new DatabaseServiceImpl();
        DatabaseService stub = (DatabaseService) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(1515);
        registry.bind("DatabaseService", stub);
    }

}
