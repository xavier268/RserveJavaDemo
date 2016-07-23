/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import com.twiceagain.rservejavademo.webaccess.BasicDriver;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Compute the feature tree of a full page.
 *
 * @author xavier
 */
public class PageFeatures {

    protected WebDriver wd;
    protected FTree tree;
    protected String url;

/**
 * Use an open existing page.
 * @param wd 
 */
    public PageFeatures(WebDriver wd) {
        this.wd = wd;
        initTree();
    }
/**
 * 
 * @param url 
 */
    public PageFeatures(String url) {
        if (wd == null) {
            wd = BasicDriver.getDriver();
        }
        wd.get(url);
        initTree();
    }
    
    private void initTree() {
        System.out.println("Starting to analyse page, please be patient ...");
        WebElement we = wd.findElement(By.xpath("/*"));  // Start at the root, usually html
        url = wd.getCurrentUrl();
        System.out.println("Loaded page : " + url);
        tree = new FTree(we,null);        
    }
    
    public void close() {
        if(wd != null) {
            wd.close();
            wd=null;
        }
    }

    @Override
    public String toString() {
        return (tree.toStringAll());
    }

    public WebDriver getDriver() {
        return wd;
    }

    public FTree getTreeRoot() {
        return tree;
    }

    public String getUrl() {
        return url;
    }

    

}
