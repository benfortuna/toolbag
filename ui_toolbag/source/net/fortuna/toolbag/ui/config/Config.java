/*
 * $Id: Config.java [06-Nov-2003 22:20:22]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.config;

import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * Defines a profile configuration
 * 
 * @author Ben Fortuna
 */
public interface Config {

    /**
     * @return a map of components providing configuration of properties
     */
    List getPages();

    /**
     * @return indicates whether config is complete
     */
    boolean isComplete();

    void addChangeListener(ChangeListener listener);

    void removeChangeListener(ChangeListener listener);
}