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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author natan
 */
public class ClientApp {

    public static void main(String[] args) throws IOException, InterruptedException {

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

        ExecutorService threadPool = Executors.newCachedThreadPool();

        long sleep = 5000;
        while (sleep > 0) {
            threadPool.execute(new WriteOperation(user1));
            threadPool.execute(new WriteOperation(user2));

            Thread.sleep(sleep);
            
            System.out.println(sleep);
//            threadPool.execute(new ReadOperation());
            new ReadOperation().run();

            removeAll();
            if (sleep <= 100) {
                sleep -= 1;
            } else if (sleep <= 500) {
               sleep -= 10; 
            } else {
                sleep -= 500;
            }
        }

        threadPool.shutdown();
    }

    public static void removeAll() {
        Client client = Client.create(new DefaultClientConfig());
        WebResource webResource = client.resource("http://localhost:8081/alannapp");
        String texto = webResource.get(String.class);
//        System.out.println(texto);
    }
}
