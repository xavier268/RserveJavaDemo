/*
 * Webautomation - tools to automate data collection from the web
 * 
 * Copyright (C) 2015,2016 Twice Again SAS and Xavier Gandillot<xavier@gandillot.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * Also, be aware that automating data collection from the web may 
 * be UNLAWFULL or INAPPROPRIATE, depending on your country, 
 * the way you do it, and the applicable local laws.
 * If you ever use these tools, use them with TACT and MEASURE, 
 * and always ensure you are NEVER impacting any site operation.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.twiceagain.rservejavademo.webaccess;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Static class to provide basic chrome webdriver instances. (you need to have
 * installed the 'marionette' ChromeDriver fisrt )
 *
 * @author xavier
 */
public class BasicDriver  {
    
    private BasicDriver() {
    }
    
    /**
     * Return a valid driver
     * @return 
     */
    public static WebDriver getDriver() {   
        // Required to help locate the binary that needs to be  downloaded manually :-(
        // See : https://sites.google.com/a/chromium.org/chromedriver/getting-started
        System.setProperty("webdriver.chrome.driver", "/home/xavier/bin/chromedriver");
        return new ChromeDriver();
    }
}
