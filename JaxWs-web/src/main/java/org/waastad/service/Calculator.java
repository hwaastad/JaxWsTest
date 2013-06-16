/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import no.datametrix.smartguest.ejb.model.EventLogMessage;
import no.datametrix.smartguest.ejb.qualifier.EventLog;
import no.datametrix.smartguest.ejb.service.AdminService;
import no.datametrix.smartguest.ejb.service.RegisterService;
import org.waastad.ejb.BusinessBean;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@DeclareRoles({"SuperAdminGroup", "ExternalAdminGroup"})
@Stateless
@WebService(
        portName = "CalculatorPort",
        serviceName = "CalculatorService",
        targetNamespace = "http://superbiz.org/wsdl",
        endpointInterface = "org.waastad.service.CalculatorWs")
public class Calculator implements CalculatorWs {
    @EJB
    private BusinessBean businessBean;
    @Resource
    WebServiceContext wsContext;
    @EJB
    private RegisterService registerService;
    @EJB
    private AdminService adminService;
    @Inject
    @EventLog
    private Event<EventLogMessage> eventLog;

    @Override
    //@RolesAllowed({"SuperAdminGroup", "ExternalAdminGroup"})
    public int sum(int add1, int add2) {
        System.out.println("From hello: "+ businessBean.sayHello("helge"));
        return add1 + add2;
    }
}
