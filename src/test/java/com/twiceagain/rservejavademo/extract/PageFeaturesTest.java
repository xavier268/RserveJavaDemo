/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.rservejavademo.extract;

import org.junit.Test;

/**
 *
 * @author xavier
 */
public class PageFeaturesTest {

    public PageFeaturesTest() {
    }

    /**
     * Test of toString method, of class PageFeatures.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testToString() throws InterruptedException {
        PageFeatures fe = new PageFeatures("http://www.amazon.com");
        System.out.println(fe);
        Long c = fe.getTreeRoot().streamChildren().count();
        System.out.printf("Counted %d children\n",c);
        fe.close();
    }

}
