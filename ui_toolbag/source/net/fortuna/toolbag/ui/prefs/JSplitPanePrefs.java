/*
 * $Id: JSplitPanePrefs.java [17-Mar-2004 00:17:20]
 * 
 * Copyright (c) 2004 b.fore Solutions
 */
package net.fortuna.toolbag.ui.prefs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSplitPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Preferences for JSplitPanes
 * 
 * @author Ben Fortuna
 */
public class JSplitPanePrefs extends ComponentPrefs implements
        PropertyChangeListener {

    private static final Log log = LogFactory.getLog(JSplitPanePrefs.class);

    private JSplitPane pane;

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param pane
     */
    protected JSplitPanePrefs(JSplitPane pane) {

        this(pane, null);
    }

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param pane
     * @param id
     */
    protected JSplitPanePrefs(JSplitPane pane, String id) {

        super(pane, id);

        this.pane = pane;

        pane.setDividerLocation(getDividerLocation());

        pane.addPropertyChangeListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent e) {

        log.debug("Property changed: " + e.getPropertyName());

        if ("dividerLocation".equals(e.getPropertyName())) {

            if (getId() != null) {

                getPreferences().putInt(pane.getClass().getName() + getId()
                        + ".dividerLocation", pane.getDividerLocation());
            }

            getPreferences().putInt(pane.getClass().getName() + ".dividerLocation", pane
                    .getDividerLocation());
        }
    }

    public int getDividerLocation() {

        if (getId() != null) {

        return getPreferences().getInt(
                pane.getClass().getName() + getId() + ".dividerLocation", pane
                        .getDividerLocation()); }

        return getPreferences().getInt(pane.getClass().getName() + ".dividerLocation",
                pane.getDividerLocation());
    }
}