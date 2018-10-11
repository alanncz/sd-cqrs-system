/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.postgresapp.jdbc;

import com.natansevero.shared.model.Usuario;
import com.natansevero.shared.services.DatabaseService;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.natansevero.shared.services.TxDatabaseService;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author alann
 */
public class DatabaseServiceImpl implements DatabaseService, TxDatabaseService {

    private static final String URL = "jdbc:postgresql://localhost:5434/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private boolean inTransaction;
    private ResultSet transResult;

    public DatabaseServiceImpl() {
        this.inTransaction = false;
        this.transResult = null;
    }

    @Override
    public boolean inserir(Usuario usuario) throws RemoteException {
        if (inTransaction) {
            try {
                this.transResult.moveToInsertRow();
                this.transResult.updateString(1, usuario.getUuid());
                this.transResult.updateString(2, usuario.getNome());
                this.transResult.updateString(3, usuario.getImageUrl());
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new RemoteException("Não deu pra inserir...");
        }
        return false;
    }

    @Override
    public Usuario buscar(String uuid) throws RemoteException {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from usuario where uuid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            Usuario usuario = new Usuario();
            rs.next();
            usuario.setUuid(rs.getString("uuid"));
            usuario.setNome(rs.getString("nome"));
            usuario.setImageUrl(rs.getString("imageurl"));
            return usuario;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> buscarTodos() throws RemoteException {
        return null;
    }

    @Override
    public void prepare() throws RemoteException {
        inTransaction = true;
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from usuario";
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            ResultSet rs = stmt.executeQuery(sql);
            this.transResult = rs;
            //rs.moveToInsertRow();
            //rs.insertRow();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void roolback() throws RemoteException {
        if (inTransaction) {
            this.transResult = null;
            inTransaction = false;
        }
    }

    @Override
    public void commit() throws RemoteException{
        if (inTransaction) {
            try {
                this.transResult.insertRow();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            inTransaction = false;
        }
    }

}
