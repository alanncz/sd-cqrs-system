/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.natanapp.main;

import com.natansevero.natanapp.resource.UsuarioResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 *
 * @author natan
 */
public class NatanApp {
    public static void main(String[] args) throws Exception {
        int port = 8081;

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, port);
        
        Application app = new Application();
        component.getDefaultHost().attach("/app", app);
        
        Router router = new Router();
        router.attach("/usuarios", UsuarioResource.class);
        
        app.setInboundRoot(router);

        component.start();
    }
}
