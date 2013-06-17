/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jaas;

import java.security.Principal;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class UserPrincipal implements Principal {

    private String name;

    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
