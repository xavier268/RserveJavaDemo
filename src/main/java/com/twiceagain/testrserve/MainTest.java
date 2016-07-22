/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.testrserve;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 *
 * @author xavier
 */
public class MainTest {
    private static final Logger LOG = Logger.getLogger(MainTest.class.getName());
    private static int maxretry = 0;
    /**
     * Run a test connection with the Rserve daemon
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException  { 
        if(maxretry > 3) {
            LOG.warning("Max retry reached - aborting");
            System.exit(-1);
        }
        maxretry += 1;
        try {
            LOG.log(Level.INFO, "Attempting to connect to Rserve, localhost:6311, attempt #{0}", maxretry);
            RConnection c = new RConnection();
            
            int v = c.getServerVersion();
            LOG.log(Level.INFO, "Server version : {0}", v);
            
            String s = c.eval("R.home()").asString();
            LOG.log(Level.INFO, "R_Home directory : {0}", s);
            
            double[] ds = c.eval("c(1,2,3,4.5)").asDoubles();
            LOG.log(Level.INFO, "Returned double array : {0}", Arrays.toString(ds));
            
            c.eval("l <- list('un','deux','trois',55)");
            RList ls = c.eval("l").asList();
            LOG.log(Level.INFO, "Returned list values : {0}", ls.values().toString());
            LOG.log(Level.INFO, "Returned list keys : {0}", ls.keys());
            c.close();        
            LOG.info("Sucessfully closed Rserve session");
            
            // Stopping Rserve
            RserveManager.stop();
            
        } catch (RserveException ex) {
            LOG.warning("Rserve was not runnning ... attempting to restart it");
            RserveManager.start();
            main(args);
        } catch (REXPMismatchException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }
    
}
