/*
 * $Id: ComponentPrefs.java [17-Mar-2004 00:05:56]
 *
 * Copyright (c) 2004 b.fore Solutions
 */
package net.fortuna.toolbag.ui.prefs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

/**
 * Preferences for components
 *
 * @author Ben Fortuna
 */
public class ComponentPrefs implements ComponentListener {

    private Preferences preferences;

    private Component component;

    private String id;

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param component a component to monitor for preference
     * changes
     */
    public ComponentPrefs(final Component component) {
        this(component, component.getName());
    }

    /**
     * Constructor made protected to ensure only instantiated
     * by PrefsManager.
     * @param component a component to monitor for preference
     * changes
     * @param id the identifier of a specific component
     */
    public ComponentPrefs(final Component component, final String id) {
        this.component = component;
        this.id = id;

        preferences = Preferences.userNodeForPackage(component.getClass());

        component.setLocation(getLocation());
        component.setSize(getSize());

        component.addComponentListener(this);
    }

    /**
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(final ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public final void componentMoved(final ComponentEvent e) {
        // save preferences on component location..
        if (id != null) {
            getPreferences().putInt(component.getClass().getName() + id + ".x",
                            component.getX());
            getPreferences().putInt(component.getClass().getName() + id + ".y",
                            component.getY());
        }

        getPreferences().putInt(component.getClass().getName() + ".x", component.getX());
        getPreferences().putInt(component.getClass().getName() + ".y", component.getY());
    }

    /**
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    public final void componentResized(final ComponentEvent e) {
        // save preferences on component size..
        if (id != null) {
            getPreferences().putInt(component.getClass().getName() + id + ".width",
                            component.getWidth());
            getPreferences().putInt(component.getClass().getName() + id + ".height",
                            component.getHeight());
        }

        getPreferences().putInt(component.getClass().getName() + ".width",
                        component.getWidth());
        getPreferences().putInt(component.getClass().getName() + ".height",
                        component.getHeight());
    }

    /**
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(final ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Returns the saved location preferences for the component.
     * @return a point specifying a location
     */
    public final Point getLocation() {
        if (id != null) {
            return new Point(getPreferences().getInt(component.getClass().getName() + id + ".x",
                                            component.getLocation().x),
                                            getPreferences().getInt(component.getClass().getName() + id + ".y",
                                            component.getLocation().y));
        }

        return new Point(getPreferences().getInt(component.getClass().getName() + ".x",
                                        component.getLocation().x),
                                        getPreferences().getInt(component.getClass().getName() + ".y",
                                        component.getLocation().y));
    }

    /**
     * Returns the saved size preferences for the component.
     * @return a dimension specifying a size
     */
    public final Dimension getSize() {
        if (id != null) {
            return new Dimension(getPreferences().getInt(component.getClass().getName() + id + ".width",
                                                component.getSize().width),
                                                getPreferences().getInt(component.getClass().getName() + id + ".height",
                                                component.getSize().height));
        }

        return new Dimension(getPreferences().getInt(component.getClass().getName() + ".width",
                                            component.getSize().width),
                                            getPreferences().getInt(component.getClass().getName() + ".height",
                                            component.getSize().height));
    }

    /**
     * @return Returns the component.
     */
    public final Component getComponent() {
        return component;
    }

    /**
     * @return Returns the id.
     */
    public final String getId() {
        return id;
    }

    /**
     * @return Returns the preferences.
     */
    public final Preferences getPreferences() {
        return preferences;
    }
}