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
        FeatureExtractor fe = new FeatureExtractor("http://www.google.com");
        System.out.println(fe);
        fe.close();
    }

}
