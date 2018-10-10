/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.client.main;

import com.natansevero.shared.model.Usuario;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.IOException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author natan
 */
public class ClientApp {
    public static void main(String[] args) throws IOException  {

        String uuid = "hsdahufei";
        String name = "Alann";
        String imageLink = "https://www.comboinfinito.com.br/principal/wp-content/uploads/2017/04/goku-dragon-ball.jpg";
        Usuario user = new Usuario();
        user.setUuid(uuid);
        user.setNome(name);
        user.setImageUrl(imageLink);
        Client client = Client.create(new DefaultClientConfig());
        WebResource webResource = client.resource("http://localhost:8080/alannapp");
        String texto = webResource.type(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(texto);
        webResource.type(MediaType.APPLICATION_XML).post(user);
        System.in.read();

   }
}
