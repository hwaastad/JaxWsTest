/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import java.net.URL;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class CalculatorWsTest {

    public CalculatorWsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Properties properties = new Properties();
        properties.setProperty("openejb.embedded.remotable", "true");
        //properties.setProperty("httpejbd.print", "true");
        //properties.setProperty("httpejbd.indent.xml", "true");
        EJBContainer.createEJBContainer(properties);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sum method, of class CalculatorWs.
     */
    @Test
    public void testSum() throws Exception {
        System.out.println("sum");
        Service calculatorService = Service.create(new URL("http://127.0.0.1:4204/JaxWs-web/Calculator?wsdl"),
                new QName("http://superbiz.org/wsdl", "CalculatorService"));
        
        Assert.assertNotNull(calculatorService);
        
        CalculatorWs calculator = calculatorService.getPort(CalculatorWs.class);
        //Assert.assertEquals(10, calculator.sum(4, 6));
    }

}