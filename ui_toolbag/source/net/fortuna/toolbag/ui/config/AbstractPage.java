/*
 * $Id: ConfigPage.java [12-Nov-2003 23:38:25]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.config;

import java.awt.GridBagLayout;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Base class for configuration pages
 * 
 * @author Ben Fortuna
 */
public abstract class AbstractPage extends JPanel {

    protected Properties properties;

    private ChangeEvent changeEvent = null;

    public AbstractPage(Properties properties) {

        super(new GridBagLayout());

        this.properties = properties;
    }

    public void addChangeListener(ChangeListener listener) {

        listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(ChangeListener listener) {

        listenerList.remove(ChangeListener.class, listener);
    }

    protected void fireStateChanged() {

        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {

            if (listeners[i] == ChangeListener.class) {

                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }

                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }
}