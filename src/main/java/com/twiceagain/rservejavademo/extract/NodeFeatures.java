/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

/**
 * Accesses the features for a given node.
 *
 * @author xavier
 */
public class NodeFeatures {

    WebElement we;
    private boolean visible;

    public NodeFeatures(WebElement we) {
        this.we = we;
    }

    public boolean isDisplayed() {
        return visible = (we != null && we.isDisplayed());
    }

    public String getTagName() {
        return we.getTagName().toLowerCase();
    }

    public String getText() {
        return visible ? we.getText() : "";
    }

    public Dimension getSize() {
        try {
            return we.getSize();
        } catch (Exception ex) {
            return new Dimension(0, 0);
        }
    }

    /**
     * Ratio of width / height
     *
     * @return ratio, or 0 if not visible
     */
    public double getFormFactor() {
        try {
            Dimension r = getSize();
            return (1.0 * r.width / r.height);
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * Visible surface of element
     *
     * @return
     */
    public double getArea() {
        Dimension r = getSize();
        return r.width * r.height;
    }

    /**
     * Density of the textual content
     *
     * @return
     */
    public double getTextDensity() {
        return visible ? (getText().length() / getArea()) : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTagName());
        sb.append("\t");
        sb.append(isDisplayed());
        sb.append("\t");
        sb.append(getArea());
        sb.append("\t");
        sb.append(getSize().width);
        sb.append("-");
        sb.append(getSize().height);
        sb.append("\t");
        sb.append(getTextDensity());
        sb.append("\t");

        return sb.toString();
    }

}
