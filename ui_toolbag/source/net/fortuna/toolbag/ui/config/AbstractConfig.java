/*
 * $Id: AbstractConfig.java [06-Nov-2003 22:29:45]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * Abstract base implementation of profile configuration
 * 
 * @author Ben Fortuna
 */
public abstract class AbstractConfig implements Config {

    private List pages;

    /**
     * @see Config#getPages()
     */
    public final List getPages() {

        if (pages == null) {
            pages = new ArrayList();
        }

        return pages;
    }

    /**
     * @see Config#addChangeListener(javax.swing.event.ChangeListener)
     */
    public final void addChangeListener(final ChangeListener listener) {

        // add change listener to all pages..
        for (Iterator i = getPages().iterator(); i.hasNext();) {
            AbstractPage page = (AbstractPage) i.next();
            page.addChangeListener(listener);
        }
    }

    /**
     * @see Config#removeChangeListener(javax.swing.event.ChangeListener)
     */
    public final void removeChangeListener(final ChangeListener listener) {

        // remove change listener from all pages..
        for (Iterator i = getPages().iterator(); i.hasNext();) {
            AbstractPage page = (AbstractPage) i.next();
            page.removeChangeListener(listener);
        }
    }
}