/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import com.twiceagain.rservejavademo.webaccess.BasicDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author xavier
 */
public class FeatureExtractorTest {

    public FeatureExtractorTest() {
    }

    /**
     * Test of toString method, of class FeatureExtractor.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testToString() throws InterruptedException {
        WebDriver wd = BasicDriver.getDriver();
        wd.get("http://www.google.fr");
        System.out.println((new FeatureExtractor(wd)));
        Thread.sleep(5000);
        wd.close();
    }

}
