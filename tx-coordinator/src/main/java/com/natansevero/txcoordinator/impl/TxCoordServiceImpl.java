/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.txcoordinator.impl;

import com.natansevero.shared.services.TxDatabaseService;
import java.rmi.RemoteException;
import java.util.List;
import com.natansevero.shared.services.TxCoordService;

/**
 *
 * @author natan
 */
public class TxCoordServiceImpl implements TxCoordService {

    private List<TxDatabaseService> txDatabases;
    
    public TxCoordServiceImpl(List<TxDatabaseService> txDatabases) throws RemoteException {
        this.txDatabases = txDatabases;
    }
    
    @Override
    public void prepareAll() throws RemoteException {
        for(TxDatabaseService tcs: this.txDatabases) {
            tcs.prepare();
        }
    }

    @Override
    public void commitAll() throws RemoteException {
        for(TxDatabaseService tcs: this.txDatabases) {
            tcs.commit();
        }
    }

    @Override
    public void roolbackAll() throws RemoteException {
        for(TxDatabaseService tcs: this.txDatabases) {
            tcs.commit();
        }
    }
    
}
