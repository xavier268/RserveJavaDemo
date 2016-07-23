/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.*;

/**
 *
 * @author xavier
 */
public class PageFeaturesTest {

    static PageFeatures fe;

    public PageFeaturesTest() {
    }

    @BeforeClass
    public static void prepare() {
       // fe = new PageFeatures("https://news.google.fr/");
        fe = new PageFeatures("http://www.google.com/");
    }

    /**
     * Test of toString method, of class PageFeatures.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testToString() throws InterruptedException {
        System.out.printf("Printing the PageFeatures object\n%s",fe);
        Long c = fe.getTreeRoot().streamChildren().count();
        System.out.printf("\nCounted %d children\n", c);
    }
    
    @Test
    public void testFiltering() {
        System.out.printf("Printing the filtered objects (visible)\n");
        String s = fe.getTreeRoot()
                .streamChildren()
                .filter(FTree::isVisible)
                .map(FTree::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(s);
        
    }

    @AfterClass
    public static void cleanup() {
        fe.close();
    }

}
