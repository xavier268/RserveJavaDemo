/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Structure to duplicate the dom tree of a page, while calculating relevant
 * features.
 *
 * @author xavier
 */
public class FWebElement {

    WebElement we;
    List<FWebElement> children = new ArrayList<>();
    FWebElement parent = null;
    int depth = 0;
    StringBuilder sb = new StringBuilder();
    StringBuilder path = new StringBuilder();

    public FWebElement(WebElement we, FWebElement parent) {
        this.we = we;
        if (parent != null) {
            this.parent = parent;
            depth = parent.depth + 1;
            path.append(parent.path);
            path.append("/");
            path.append(we.getTagName());

            sb.append(we.getTagName());
            sb.append("\t");
            sb.append(we.isDisplayed());
            sb.append("\t");
            sb.append(path);
            sb.append("\n");

        }
        if (we != null) {
            List<WebElement> lwe = we.findElements(By.xpath("./*"));
            for (WebElement w : lwe) {
                FWebElement cc = new FWebElement(w, this);
                children.add(cc);
                sb.append(cc.toString());
            }
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public String getPath() {
        return path.toString();
    }

}
