package net.fortuna.toolbag.ui.config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.fortuna.toolbag.ui.util.WindowUtils;

/**
 * @author Ben Fortuna
 * @deprecated Should use ConfigPane instead
 */
public class ConfigurationPane extends JPanel {

    // the current configuration properties..
    private Properties properties;

    public ConfigurationPane(final Properties p) {
        setProperties(p);
    }

    /**
     * Gets the properties.
     * 
     * @return Returns a Properties
     */
    public final Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
        }

        return properties;
    }

    /**
     * Sets the properties.
     * 
     * @param properties
     *            The properties to set
     */
    public final void setProperties(final Properties properties) {
        this.properties = properties;
    }

    public final JDialog createDialog(final String title, final String propertiesFile) {
        final JDialog dialog = new JDialog((Frame) null, title, true);
        dialog.getContentPane().add(this, BorderLayout.CENTER);

        // create save/cancel buttons..
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {

            public final void actionPerformed(final ActionEvent e) {
                saveConfiguration(propertiesFile, getProperties());
                dialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            public final void actionPerformed(final ActionEvent e) {
                dialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        dialog.getRootPane().setDefaultButton(saveButton);

        dialog.pack();

        WindowUtils.center(dialog);

        return dialog;
    }

    public static Properties loadConfiguration(final String filename) {
        return loadConfiguration(filename, null);
    }

    public static Properties loadConfiguration(final String filename, final Class c) {
        System.out.println("Loading configuration: [" + filename + "]");

        //ClassLoader cl = ClassLoader.getSystemClassLoader();

        // load defaults..
        Properties defaults = new Properties();

        /*
         * try { defaults.load(cl.getResourceAsStream("/"+filename)); } catch
         * (Exception e) { e.printStackTrace(); }
         */

        // try loading from screensaver resource..
        if (c != null) {
            try {
                defaults.load(c.getResourceAsStream("/" + filename));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                defaults.load(ClassLoader.getSystemResourceAsStream(filename));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        Properties configuration = new Properties(defaults);

        // load any additional configuration for current user..
        try {
            File configFile = new File(System.getProperty("user.home"),
                    filename);
            configuration.load(new FileInputStream(configFile));
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Cannot load user properties [" + e + "]");
        }

        return configuration;
    }

    public static void saveConfiguration(final String filename,
            final Properties configuration) {
        try {
            File configFile = new File(System.getProperty("user.home"),
                    filename);
            configuration.store(new FileOutputStream(configFile), null);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}