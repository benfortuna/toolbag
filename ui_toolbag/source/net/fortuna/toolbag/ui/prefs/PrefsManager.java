/*
 * $Id: PrefsManager.java [08-Jun-2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.prefs;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSplitPane;

/**
 * Manages the monitoring of user interface preferences.
 * @author benfortuna
 */
public final class PrefsManager {

    private static PrefsManager instance = new PrefsManager();
    
    private Map componentPrefs;
    
    private Map jSplitPanePrefs;
    
    /**
     * Constructor made private to enforce singleton.
     */
    private PrefsManager() {
        componentPrefs = new HashMap();
        
        jSplitPanePrefs = new HashMap();
    }

    /**
     * @return Returns the instance.
     */
    public static PrefsManager getInstance() {
        return instance;
    }
    
    /**
     * Registers a component for preferences monitoring.
     * @param component a component to monitor
     * @param id the id of a specific component
     */
    public void registerComponent(final Component component, final String id) {
        if (componentPrefs.get(id) == null) {
            componentPrefs.put(id, new ComponentPrefs(component));
        }
    }
    
    /**
     * Registers a JSplitPane for preferences monitoring.
     * @param pane a JSplitPane to monitor
     * @param id the id of a specific JSplitPane
     */
    public void registerJSplitPane(final JSplitPane pane, final String id) {
        if (jSplitPanePrefs.get(id) == null) {
            jSplitPanePrefs.put(id, new JSplitPanePrefs(pane));
        }
    }
}
