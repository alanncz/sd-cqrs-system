/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.txcoordinator.impl;

import com.natansevero.shared.services.TxCoorService;
import com.natansevero.shared.services.TxCoordinatorService;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author natan
 */
public class TxCoorServiceImpl implements TxCoorService {

    private List<TxCoordinatorService> txCoordinators;
    
    public TxCoorServiceImpl(List<TxCoordinatorService> txCoordinators) throws RemoteException {
        this.txCoordinators = txCoordinators;
    }
    
    @Override
    public void prepareAll() throws RemoteException {
        for(TxCoordinatorService tcs: this.txCoordinators) {
            tcs.prepare();
        }
    }

    @Override
    public void commitAll() throws RemoteException {
        for(TxCoordinatorService tcs: this.txCoordinators) {
            tcs.commit();
        }
    }

    @Override
    public void roolback() throws RemoteException {
        for(TxCoordinatorService tcs: this.txCoordinators) {
            tcs.commit();
        }
    }
    
}
