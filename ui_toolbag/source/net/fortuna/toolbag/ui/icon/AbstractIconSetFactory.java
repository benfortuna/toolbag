/*
 * $Id: AbstractIconSetFactory.java [18/07/2004]
 *
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.icon;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for all icon set factories. An icon set factory
 * is responsible for creating and caching different types of
 * icon sets.
 * @author benfortuna
 */
public abstract class AbstractIconSetFactory {

    private static final String DEFAULT_ID = DefaultIconSetFactory.class.getName();

    private static Log log = LogFactory.getLog(AbstractIconSetFactory.class);
    
    private static Map instances = new HashMap();
    
    /**
     * Constructor made protected to enforce singleton rules.
     */
    protected AbstractIconSetFactory() {
    }

    /**
     * Returns a shared instance of AbstractIconSetFactory
     * identified by the specified identifier.
     * @param id an AbstractIconSetFactory type
     * @return an AbstractIconSetFactory
     */
    public static AbstractIconSetFactory getInstance(String id) {
        AbstractIconSetFactory instance = (AbstractIconSetFactory) instances.get(id);
        
        if (instance == null) {
            try {
                instance = (AbstractIconSetFactory) Class.forName(id).newInstance();
                
                instances.put(id, instance);
            }
            catch (Exception e) {
                log.error("Error creating instance", e);
            }
        }
        
        return instance;
    }
    
    /**
     * Returns a shared instance of the default
     * AbstractIconSetFactory type.
     * @return an AbstractIconSetFactory
     */
    public static AbstractIconSetFactory getDefaultInstance() {
        return getInstance(DEFAULT_ID);
    }
    
    /**
     * Returns the icon set with the specified id.
     * @param id
     * @return an icon set, or null if icon set not found
     */
    public abstract IconSet getIconSet(String id);
    
    /**
     * Returns the default icon set.
     * @return an icon set, or null if the default icon set
     * is not found
     */
    public abstract IconSet getDefaultIconSet();
    
    /**
     * Returns an array of icon set identifiers.
     * @return a string array
     */
    public abstract String[] getAvailableIds();
}
