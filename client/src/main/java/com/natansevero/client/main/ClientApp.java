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
    public static void main(String[] args) throws IOException, InterruptedException  {
        
        String name = "Alann";
        String imageLink = "https://www.comboinfinito.com.br/principal/wp-content/uploads/2017/04/goku-dragon-ball.jpg";
        Usuario user1 = new Usuario();
        user1.setNome(name);
        user1.setImageUrl(imageLink);
        
        String nome2 = "Natan";
        String imageLink2 = "https://i1.wp.com/ovicio.com.br/wp-content/uploads/vegeta-dragon-ball-super-995397.png?resize=663%2C373";
        Usuario user2 = new Usuario();
        user2.setNome(name);
        user2.setImageUrl(imageLink);
        
        Thread t1 = new Thread(new WriteOperation(user1));
        Thread t2 = new Thread(new WriteOperation(user2));
        t1.start();
        t2.start();
        
        Thread.sleep(2000); //apenas para teste de janela
        Thread t3 = new Thread(new ReadOperation());
        t3.start();

   }
}
