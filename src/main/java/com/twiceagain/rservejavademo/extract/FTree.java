/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Compute the feature tree of from a WebElement, duplicating its dom tree. The
 * underlying DOM is only use once, during object construction.
 *
 * @author xavier
 */
public class FTree {

    /**
     * Direct children.
     */
    private String tag = "???";
    private final List<FTree> children = new ArrayList<>();
    private FTree parent = null;
    private boolean visible = false;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private int area = 0;
    private double ratio = 0;
    private String toStringCache = null;
    private List<String> classList;
    private String id = "";
    private String type = "";
    private String text = "";
    private String href = "";
    

    /**
     * Number of parent layers
     */
    private int depth = 0;
    private final List<String> path = new ArrayList<>();
    private String src = "";

    public FTree(WebElement we) {
        initFTree(we, null);
    }

    public FTree(WebElement we, FTree parent) {
        initFTree(we, parent);
    }

    private void initFTree(WebElement we, FTree parent) {
        
        if (parent != null) {
            this.parent = parent;
            depth = parent.getDepth() + 1;

            path.addAll(parent.path);
            path.add(we.getTagName());

        }

        if (we != null) {
            tag = we.getTagName();
            visible = we.isDisplayed();
            if (isVisible()) {
                Dimension d = we.getSize();
                width = d.width;
                height = d.height;
                Point p = we.getLocation();
                x = p.x;
                y = p.y;
                area = getWidth() * getHeight();
                ratio = (getHeight() == 0) ? 0 : getWidth() / getHeight();
                text = we.getText();
            }
            id = we.getAttribute("id");
            if (getId() == null) {
                id = "";
            }
            type = we.getAttribute("type");
            if (getType() == null) {
                type = "";
            }
            String cl = we.getAttribute("class");
            if (cl != null) {
                classList = Arrays.asList(cl.split("\\s+"));
            } else {
                classList = new ArrayList();
            }

            href = we.getAttribute("href");
            if (href == null) {
                href = "";
            }

            src = we.getAttribute("src");
            if (src == null) {
                src = "";
            }

            List<WebElement> lwe = we.findElements(By.xpath("./*"));
            for (WebElement w : lwe) {
                FTree cc = new FTree(w, this);
                getChildren().add(cc);
            }
        }
    }

    /**
     * Print just this node, and not the children nodes.
     *
     * @return
     */
    @Override
    public String toString() {
        if (toStringCache != null) {
            return toStringCache;
        }
        StringBuilder sb = new StringBuilder();
        
        sb.append(getTag()).append("\t");
        sb.append(getType()).append("\t");
        sb.append(getDepth()).append("\t");
        sb.append(isVisible()).append("\t");
        sb.append(getWidth()).append("x").append(getHeight()).append("\t");
        sb.append(getX()).append(":").append(getY()).append("\t");
        sb.append(getId()).append("\t");
        sb.append(getClassList()).append("\t");
        sb.append(getPathString()).append("\t");
        String txt = (getText().length() > 51) ? getText().substring(0, 50)+"[...]" : getText();
        sb.append(txt);

        return toStringCache = sb.toString();
    }

    /**
     * Print the node and all the children
     *
     * @return
     */
    public String toStringAll() {
        StringBuilder sb = new StringBuilder();
        return streamChildren()
                .map(FTree::toString)
                .collect(Collectors.joining("\n"));
    }

    public String getPathString() {
        return path.toString();
    }
    
    

    /**
     * A sequential stream with all the child nodes from this tree, including
     * this node itself.
     *
     * @return
     *
     */
    public Stream<FTree> streamChildren() {
        return Stream.concat(Stream.of(this),
                getChildren().stream().flatMap((xx) -> xx.streamChildren())
        );
    }

    /**
     * A sequential stream of all the ancestors. The stream starts with, and
     * includes this node.
     *
     * @return
     */
    public Stream<FTree> streamAncestors() {
        List<FTree> a = new ArrayList<>();
        a.add(this);
        FTree p = this.parent;
        while (p != null) {
            a.add(p);
            p = p.parent;
        }
        return a.stream();
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return the children
     */
    public List<FTree> getChildren() {
        return children;
    }

    /**
     * @return the parent
     */
    public FTree getParent() {
        return parent;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * @return the ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * @return the classList
     */
    public List<String> getClassList() {
        return classList;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    public String getText() {
        return text;
    }

    public String getHref() {
        return href;
    }

    public String getSrc() {
        return src;
    }
    
    /**
     * List of tag name path, starting right after the root tag (usually html)
     * @return 
     */
    public List<String> getPath() {
        return path;
    }

    

}
