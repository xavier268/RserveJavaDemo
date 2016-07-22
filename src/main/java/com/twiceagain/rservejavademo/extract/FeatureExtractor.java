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

    public FeatureExtractor(WebDriver wd) {
        this.wd = wd;
    }

    public FeatureExtractor(String url) {
        if (wd == null) {
            wd = BasicDriver.getDriver();
        }
        wd.get(url);
    }

    @Override
    public String toString() {
        WebElement we = wd.findElement(By.tagName("html"));
        return toString(we);
    }

    public String toString(WebElement we) {
        StringBuilder sb = new StringBuilder();
        sb.append(new NodeFeatures(we));
        sb.append("\n");
        List<WebElement> lwe = we.findElements(By.xpath("./*"));
        for (WebElement w : lwe) {
            sb.append(toString(w));            
        }
        return sb.toString();
    }

}
