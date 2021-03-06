/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.waastad.ejb.BusinessBean;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Path("/restservice")
@Stateless
public class RestBean {
    
    @EJB private BusinessBean businessBean;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String sayHello(){
        return" hello from rest";
    }

}
