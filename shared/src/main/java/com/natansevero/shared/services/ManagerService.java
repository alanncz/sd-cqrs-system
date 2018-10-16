/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.shared.services;

import com.natansevero.shared.model.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author natan
 */
public interface ManagerService extends Remote {
    boolean inserir(Usuario usuario) throws RemoteException;
    void removerTodos() throws RemoteException;
}
