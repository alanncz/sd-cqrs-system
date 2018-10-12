/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.alannapp.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author alann
 */
public class AlannApp {
    
    public static void main(String[] args) throws IOException, URISyntaxException{
        ResourceConfig config = new ResourceConfig().packages("com.natansevero.alannapp.resource");
        URI uri = new URI("http://0.0.0.0:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        System.out.println("servidor rodando...");
        System.in.read();
        server.shutdown();
    }
    
}
