/*
 * $Id: DefaultIconSetFactory.java [04-Oct-2003 20:05:18]
 *
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A default implementation of an icon set factory. This implementation
 * looks for icon sets defined by XML-based documents in the classpath.
 * @author Ben Fortuna
 */
public final class DefaultIconSetFactory extends AbstractIconSetFactory {

    private static final String ICONSET_DEFINITION_PATH = "/icons/net/fortuna/toolbag/ui/icon/iconset.xml";

    private static Log log = LogFactory.getLog(DefaultIconSetFactory.class);

    private Map iconSets;

    private IconSet defaultIconSet;

    /**
     * Constructor made private to enforce singleton
     */
    protected DefaultIconSetFactory() {
        iconSets = new HashMap();

        // load available icon sets..
//        try {
//            Enumeration iconSetUrls = getClass().getClassLoader().getResources(ICONSET_DEFINITION_PATH);
            URL url = getClass().getResource(ICONSET_DEFINITION_PATH);

//            while (iconSetUrls.hasMoreElements()) {
            if (url != null) {
//                URL url = (URL) iconSetUrls.nextElement();

                // debugging..
                log.info("Loading icon set [" + url + "]");

                try {
                    IconSet iconSet = new DefaultIconSet(url);

                    iconSets.put(iconSet.getId(), iconSet);

                    // just use the first icon set found as the default..
                    if (defaultIconSet == null) {
                        defaultIconSet = iconSet;
                    }
                }
                catch (Exception e) {
                    log.warn("Error loading icon set [" + url + "]", e);
                }
            }
//        }
//        catch (IOException ioe) {
//            log.error("Error loading icon sets", ioe);
//        }
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.AbstractIconSetFactory#getIconSet(java.lang.String)
     */
    public IconSet getIconSet(String id) {
        return (IconSet) iconSets.get(id);
    }

    /**
     * Returns the default icon set. This implementation simply
     * returns the first icon set found in the classpath.
     * @return an icon set, or null if the default icon set
     * is not found
     */
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.AbstractIconSetFactory#getDefaultIconSet()
     */
    public IconSet getDefaultIconSet() {
        return defaultIconSet;
    }


    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.AbstractIconSetFactory#getAvailableIds()
     */
    public String[] getAvailableIds() {
        return (String[]) iconSets.keySet().toArray(new String[iconSets.size()]);
    }
}