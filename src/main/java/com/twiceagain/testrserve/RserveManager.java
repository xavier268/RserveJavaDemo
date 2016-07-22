/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.testrserve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manage Rserve daemon from java
 *
 * Inspired from :
 * http://stackoverflow.com/questions/32373372/how-to-start-rserve-automatically-from-java
 *
 * @author xavier
 */
public class RserveManager {

    private static final Logger LOG = Logger.getLogger(RserveManager.class.getName());
    private static final String RSERVEBINARY = "/home/xavier/R/x86_64-redhat-linux-gnu-library/3.3/Rserve/libs/Rserve --vanilla";
    // Make sure you use the shared lib directory !
    private static final String RHOME="/usr/lib64/R";

    public static void start() {
        LOG.info("Starting Rserve");
        runCommand(RSERVEBINARY);
    }

    public static void stop() throws InterruptedException {
        
        LOG.info("Stopping Rserve");
        runCommand("killall -15 Rserve");
        LOG.info("Done.");
    }

    private static void runCommand(String command) {

        String s;
        if (command == null || command.isEmpty()) {
            return;
        }

        try {
            String[] envp = {"R_HOME="+RHOME};
            Process p = Runtime.getRuntime().exec(command, envp);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            
            // Display command output
            while ((s = stdInput.readLine()) != null) {
                LOG.log(Level.INFO, "R said > {0}", s);
            }

            

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Cannot run command : {0}", command);
            e.printStackTrace();
        }
    }

}
