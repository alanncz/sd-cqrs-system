/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.client.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alann
 */
public class ReadOperation implements Runnable {

    @Override
    public void run() {
        Client client = Client.create(new DefaultClientConfig());
        WebResource webResource = client.resource("http://localhost:8081/app/usuarios");
        String texto = webResource.type(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(texto);
    }
    
}
