/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.postgresapp.jdbc;

import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.DatabaseService;
import com.natansevero.shared.services.TxCoordinatorService;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class DatabaseServiceImpl implements DatabaseService, TxCoordinatorService {

    private static final String URL = "jdbc:postgresql://database:5432/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private boolean inTransaction;

    public DatabaseServiceImpl() {
        this.inTransaction = false;
    }

    @Override
    public boolean inserir(Usuario usuario) throws RemoteException {
        if (inTransaction) {
            try {

                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                String sql = "insert into usuario (nome,imageurl,uuid) values(?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, usuario.getNome());
                    ps.setString(2, usuario.getImageUrl());
                    ps.setString(3, usuario.getUuid());
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else throw new RemoteException("Não deu pra inserir...");
        return false;
    }

    @Override
    public Usuario buscar(String uuid) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscarTodos() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
