/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.shared.model;

/**
 *
 * @author natan
 */
public class Usuario {
    private String uuid;
    private String nome;
    private String imageUrl;

    public Usuario() {
        
    }
    
    public Usuario(String uuid, String nome, String imageUrl) {
        this.uuid = uuid;
        this.nome = nome;
        this.imageUrl = imageUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Usuario{" + "uuid=" + uuid + ", nome=" + nome + ", imageUrl=" + imageUrl + '}';
    }
    
    
}
