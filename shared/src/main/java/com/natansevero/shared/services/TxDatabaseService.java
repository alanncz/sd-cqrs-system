/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.shared.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author natan
 */
public interface TxDatabaseService extends Remote {
    void prepare() throws RemoteException;
    void roolback() throws RemoteException;
    void commit() throws RemoteException;
}
