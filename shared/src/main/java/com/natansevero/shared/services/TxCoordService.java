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
public interface TxCoordService extends Remote {
    void prepareAll() throws RemoteException;
    void commitAll() throws RemoteException;
    void roolbackAll() throws RemoteException;
}
