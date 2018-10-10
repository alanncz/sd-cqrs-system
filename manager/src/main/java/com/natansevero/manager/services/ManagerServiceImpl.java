/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.manager.services;

import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.DatabaseService;
import com.natansevero.shared.services.ManagerService;
import com.natansevero.shared.services.TxCoordService;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author alann
 */
public class ManagerServiceImpl implements ManagerService {

    private final TxCoordService txCoord;
    private final List<DatabaseService> listDatabaseServices;

    public ManagerServiceImpl(TxCoordService txCoord, List<DatabaseService> listDatabaseServices) {
        this.txCoord = txCoord;
        this.listDatabaseServices = listDatabaseServices;

    }

    @Override
    public boolean inserir(Usuario usuario) throws RemoteException {
        txCoord.prepareAll();

        try {
            for (DatabaseService objeto : listDatabaseServices) {
                objeto.inserir(usuario);
            }
            txCoord.commitAll();
            return true;
        }catch(RemoteException e){
            txCoord.roolbackAll();
            throw new RemoteException(e.getMessage());
        }
        

    }

}
