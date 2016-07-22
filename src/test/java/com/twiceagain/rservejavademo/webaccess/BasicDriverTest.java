/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.webaccess;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author xavier
 */
public class BasicDriverTest {
    
    public BasicDriverTest() {
    }

    /**
     * Test of _getDriver method, of class BasicDriver.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testGetDriver() throws InterruptedException {
        System.out.println("getDriver");
        WebDriver expResult = null;
        WebDriver wd = BasicDriver.getDriver();
        wd.get("http://www.google.fr");        
        Thread.sleep(1000);
        assert(wd.getTitle().contains("Google"));
        WebElement searchBox = wd.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver test");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        wd.close();        
    }
    
   

    
    
}
