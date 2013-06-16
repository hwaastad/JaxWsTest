/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
public class BusinessBeanImpl implements BusinessBean {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
