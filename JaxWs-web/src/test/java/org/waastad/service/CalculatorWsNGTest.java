/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import java.net.URL;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.openejb.testing.MockInjector;
import org.apache.openejb.testing.Module;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.waastad.ejb.BusinessBeanImpl;
import static org.mockito.Mockito.*;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class CalculatorWsNGTest {
    
    @Mock
    private BusinessBeanImpl businessBean;
    private InitialContext initialContext;
    
    @MockInjector
    public Class<?> mockitoInjector() {
        return MockInjector.class;
    }
    
    @Module
    public Class<?>[] app() {
        return new Class<?>[] { CalculatorWs.class, BusinessBeanImpl.class };
    }

    public CalculatorWsNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        MockitoAnnotations.initMocks(this);
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitialContextFactory");
        properties.setProperty("openejb.embedded.remotable", "true");
        properties.setProperty("httpejbd.print", "true");
        properties.setProperty("httpejbd.indent.xml", "true");
        properties.setProperty("logging.level.OpenEJB.server.http", "FINE");
        initialContext = new InitialContext(properties);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of sum method, of class CalculatorWs.
     */
    @Test
    public void testSum() throws Exception {
        System.out.println("sum");
        when(businessBean.sayHello(Mockito.anyString())).thenReturn("What have you");
        Service calcService = Service.create(
                new URL("http://127.0.0.1:4204/JaxWs-web/Calculator?wsdl"),
                new QName("http://superbiz.org/wsdl", "CalculatorService"));

        assertNotNull(calcService);
        CalculatorWs calc = calcService.getPort(CalculatorWs.class);
        Assert.assertEquals(4, calc.sum(2, 2));
    }

}