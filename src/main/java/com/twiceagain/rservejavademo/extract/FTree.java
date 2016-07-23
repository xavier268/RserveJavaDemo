/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Compute the feature tree of from a WebElement, duplicating its dom tree.
 *
 * @author xavier
 */
public class FTree {

    WebElement we;
    /**
     * Direct children.
     */
    String tag = "???";
    List<FTree> children = new ArrayList<>();
    FTree parent = null;
    boolean visible = false;
    int x = 0, y = 0, width = 0, height = 0, area = 0;
    double ratio = 0;
    private String toStringCache = null;
    List<String> classList;
    String id = "";

    /**
     * Number of parent layers
     */
    int depth = 0;
    List<String> path = new ArrayList<>();

    public FTree(WebElement we) {
        initFTree(we, null);
    }

    public FTree(WebElement we, FTree parent) {
        initFTree(we, parent);
    }

    private void initFTree(WebElement we, FTree parent) {
        this.we = we;

        if (parent != null) {
            this.parent = parent;
            depth = parent.depth + 1;

            path.addAll(parent.path);
            path.add(we.getTagName());

        }

        if (we != null) {
            tag = we.getTagName();
            visible = we.isDisplayed();
            if (visible) {
                Dimension d = we.getSize();
                width = d.width;
                height = d.height;
                Point p = we.getLocation();
                x = p.x;
                y = p.y;
                area = width * height;
                ratio = (height == 0) ? 0 : width / height;
            }
            id = we.getAttribute("id");
            if (id == null) {
                id = "";
            }
            String cl = we.getAttribute("class");
            if (cl != null) {
                classList = Arrays.asList(cl.split("\\s+"));
            } else {
                classList = new ArrayList();
            }

            List<WebElement> lwe = we.findElements(By.xpath("./*"));
            for (WebElement w : lwe) {
                FTree cc = new FTree(w, this);
                children.add(cc);
            }
        }
    }

    @Override
    public String toString() {
        if (toStringCache != null) {
            return toStringCache;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tag).append("\t");
        sb.append(depth).append("\t");
        sb.append(visible).append("\t");
        sb.append(width).append("x").append(height).append("\t");
        sb.append(x).append(":").append(y).append("\t");
        sb.append(id).append("\t");
        sb.append(classList).append("\t");
        sb.append(getPath()).append("\n");

        for (FTree t : children) {
            sb.append(t.toString());
        }
        return toStringCache = sb.toString();
    }

    public String getPath() {
        return path.toString();
    }

    /**
     * Return a sequential stream with all the child nodes from this tree,
     * including this node itself.
     *
     * @return
     *
     */
    public Stream<FTree> streamChildren() {
        return Stream.concat(
                Stream.of(this), 
                children.stream().flatMap((xx)->xx.streamChildren())
        );
    }
}
