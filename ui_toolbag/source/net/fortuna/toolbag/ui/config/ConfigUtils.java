/*
 * Created on 15-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui.config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * @author Ben Fortuna
 *  
 */
public class ConfigUtils {

    public static Properties loadProperties(String source) throws IOException {

        try {

            return loadProperties(new URL(source));
        }
        catch (MalformedURLException mue) {

            try {

                return loadProperties(new File(source).toURL());
            }
            catch (IOException ioe) {

                // if absolute file not found, look in user
                // home for it..
                return loadProperties(new File(System.getProperty("user.home"),
                        source).toURL());
            }
        }
    }

    public static Properties loadProperties(URL url) throws IOException {

        Properties properties = new Properties();

        properties.load(url.openStream());

        return properties;
    }
}