/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.mongodbapp.impl;

import com.natansevero.mongodbapp.database.Dao;
import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.DatabaseService;
import com.natansevero.shared.services.TxDatabaseService;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author natan
 */
public class DatabaseServiceImpl implements DatabaseService, TxDatabaseService {

    private boolean inTransaction = false;
    Dao dao = Dao.getInstance();
    
    @Override
    public boolean inserir(Usuario usuario) throws RemoteException {
        dao.insert(usuario);
        return true;
    }

    @Override
    public Usuario buscar(String uuid) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscarTodos() throws RemoteException {
        return dao.listAll();
    }
    
     @Override
    public void prepare() throws RemoteException {
        inTransaction = true;
    }

    @Override
    public void roolback() throws RemoteException {
        if (inTransaction) {
            inTransaction = false;
        }
    }

    @Override
    public void commit() throws RemoteException {
        if (inTransaction) {
            inTransaction = false;
        }
    }
    
}
