/*
 * $Id: IconSet.java [17/07/2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.icon;

import javax.swing.Icon;

/**
 * Defines access to a set of icons.
 * @author benfortuna
 */
public interface IconSet {

    /**
     * Returns the identifier of the icon set.
     * @return
     */
    String getId();
    
    /**
     * Returns the name of the icon set.
     * @return
     */
    String getName();
    
    /**
     * Returns a description of the icon set.
     * @return
     */
    String getDescription();
    
    /**
     * Returns the author of the icon set.
     * @return
     */
    String getAuthor();
    
    /**
     * Returns a default icon from the icon set associated with the specified key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getIcon(final String key);

    /**
     * Returns a rollover icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getRolloverIcon(final String key);

    /**
     * Returns a disabled icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getDisabledIcon(final String key);

    /**
     * Returns a pressed icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getPressedIcon(final String key);

    /**
     * Returns a selected icon from the icon set associated with the specified
     * key.
     * @param key
     *            the key associated with the icon to return
     * @return an Icon, or null if an icon associated with the specified key
     *         cannot be found
     */
    Icon getSelectedIcon(final String key);

    /**
     * Indicates whether pressed icons are generated where a pressed icon is not
     * found.
     * @return Returns the pressedIconGenerated.
     */
    boolean isPressedIconGenerated();
}
