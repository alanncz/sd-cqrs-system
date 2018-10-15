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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author natan
 */
public class DatabaseServiceImpl implements DatabaseService, TxDatabaseService {

    private boolean inTransaction;
    private LinkedList<Usuario> transResult;
    private Dao dao;
    
    public DatabaseServiceImpl() {
        dao = Dao.getInstance();
        this.inTransaction = false;
        this.transResult = new LinkedList<>();
    }
    
    @Override
    public boolean inserir(Usuario usuario) throws RemoteException {
        // jogar na list
        transResult.add(usuario);
        
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
        // pegar os dados e jogar na transResult
        transResult = (LinkedList<Usuario>) dao.listAll();
    }

    @Override
    public void roolback() throws RemoteException {
        if (inTransaction) {
            // transResult null
            transResult = null;
            inTransaction = false;
        }
    }

    @Override
    public void commit() throws RemoteException {
        if (inTransaction) {
            // insert
            Usuario usuario = transResult.getLast();
            dao.insert(usuario);
            inTransaction = false;
        }
    }

    @Override
    public void removerTodos() throws RemoteException {
        dao.deleteAll();
    }
    
}
