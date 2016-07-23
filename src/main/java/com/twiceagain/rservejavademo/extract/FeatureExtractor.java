/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import com.twiceagain.rservejavademo.webaccess.BasicDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utilities to extract features from the nodes of the provided webpage.
 *
 * @author xavier
 */
public class FeatureExtractor {

    protected WebDriver wd;
    protected FWebElement tree;

    public FeatureExtractor(WebDriver wd) {
        this.wd = wd;
        initTree();
    }

    public FeatureExtractor(String url) {
        if (wd == null) {
            wd = BasicDriver.getDriver();
        }
        wd.get(url);
        initTree();
    }
    
    private void initTree() {
        WebElement we = wd.findElement(By.tagName("html"));        
        tree = new FWebElement(we,null);
    }
    
    public void close() {
        if(wd != null) {
            wd.close();
            wd=null;
        }
    }

    @Override
    public String toString() {
        return (tree.toString());
    }

    

}
