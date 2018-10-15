/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.shared.services;

import com.natansevero.shared.model.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author natan
 */
public interface DatabaseService extends Remote {
    boolean inserir(Usuario usuario) throws RemoteException;
    Usuario buscar(String uuid) throws RemoteException;
    List<Usuario> buscarTodos() throws RemoteException;
    void removerTodos() throws RemoteException;
}
