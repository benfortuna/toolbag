/*
 * $Id: IconSet.java [15/05/2004]
 * 
 * Copyright (c) 2004, Ben Fortuna All rights reserved.
 */
package net.fortuna.toolbag.ui.icon;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Defines a set of icons. An icon set is defined by a configuration document
 * found in the classpath under:
 * 
 * <code>/net/fortuna/toolbag/ui/icon/iconset.xml</code>
 * 
 * It is recommended to include this resource in a jar file along with the set
 * of icons you wish to use.
 * 
 * @author Ben Fortuna
 */
public class DefaultIconSet extends IconKeys implements IconSet {

    /**
     * Identifies a default icon.
     */
    private static final String DEFAULT = "default";

    /**
     * Identifies a rollover icon.
     */
    private static final String ROLLOVER = "rollover";

    /**
     * Identifies a disabled icon.
     */
    private static final String DISABLED = "disabled";

    /**
     * Identifies a pressed icon.
     */
    private static final String PRESSED = "pressed";

    /**
     * Identifies a selected icon.
     */
    private static final String SELECTED = "selected";

    private static Log log = LogFactory.getLog(DefaultIconSet.class);

    private Map icons = new HashMap();

    private Map disabledIcons = new HashMap();

    private Map rolloverIcons = new HashMap();

    private Map pressedIcons = new HashMap();

    private Map selectedIcons = new HashMap();

    private Document document;

    private boolean pressedIconGenerated;

    /**
     * Constructor.
     * 
     * @param in
     *            an input stream to load an icon set from
     * @throws ParserConfigurationException
     *             thrown if the icon set descriptor is invalid
     * @throws SAXException
     *             thrown if the icon set descriptor is not valid XML
     * @throws IOException
     *             thrown if unable to read from the specified input stream
     */
    public DefaultIconSet(final InputStream in) throws ParserConfigurationException,
            SAXException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        document = builder.parse(in);

        pressedIconGenerated = true;
    }

    /**
     * @param url a url for an icon set definition
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public DefaultIconSet(final URL url) throws ParserConfigurationException,
    SAXException, IOException {
        this(url.openStream());
    }
    
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getId()
     */
    public String getId() {
        return document.getDocumentElement().getAttribute("id");
    }
    
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getName()
     */
    public String getName() {
        return document.getDocumentElement().getElementsByTagName("name").item(0).getNodeValue();
    }
    
    
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getDescription()
     */
    public String getDescription() {
        return document.getDocumentElement().getElementsByTagName("description").item(0).getNodeValue();
    }

    
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getAuthor()
     */
    public String getAuthor() {
        return document.getDocumentElement().getElementsByTagName("author").item(0).getNodeValue();
    }
    
    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getIcon(java.lang.String)
     */
    public final Icon getIcon(final String key) {
        if (icons.get(key) == null) {
            URL url = getIconUrl(key, DEFAULT);

            try {
                icons.put(key, new ImageIcon(url));
            }
            catch (Exception e) {
                log.warn("Icon [" + key + "] not found");
                log.debug("Error loading icon [" + url + "]", e);
            }
        }

        return (Icon) icons.get(key);
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getDisabledIcon(java.lang.String)
     */
    public final Icon getDisabledIcon(final String key) {
        if (disabledIcons.get(key) == null) {
            URL url = getIconUrl(key, DISABLED);

            try {
                disabledIcons.put(key, new ImageIcon(url));
            }
            catch (Exception e) {
                log.warn("Disabled icon [" + key + "] not found");
                log.debug("Error loading disabled icon [" + url + "]", e);
            }
        }

        return (Icon) disabledIcons.get(key);
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getRolloverIcon(java.lang.String)
     */
    public final Icon getRolloverIcon(final String key) {
        if (rolloverIcons.get(key) == null) {
            URL url = getIconUrl(key, ROLLOVER);

            try {
                rolloverIcons.put(key, new ImageIcon(url));
            }
            catch (Exception e) {
                log.warn("Rollover icon [" + key + "] not found");
                log.debug("Error loading rollover icon [" + url + "]", e);
            }
        }

        return (Icon) rolloverIcons.get(key);
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getPressedIcon(java.lang.String)
     */
    public final Icon getPressedIcon(final String key) {
        if (pressedIcons.get(key) == null) {
            URL url = getIconUrl(key, PRESSED);

            try {
                pressedIcons.put(key, new ImageIcon(url));
            }
            catch (Exception e) {
                log.warn("Pressed icon [" + key + "] not found");
                log.debug("Error loading pressed icon [" + url + "]", e);
            }
        }

        // generate a pressed icon if not found..
        if (pressedIcons.get(key) == null && pressedIconGenerated) {
            pressedIcons.put(key, generatePressedIcon(getIcon(key)));
        }

        return (Icon) pressedIcons.get(key);
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#getSelectedIcon(java.lang.String)
     */
    public final Icon getSelectedIcon(final String key) {
        if (selectedIcons.get(key) == null) {
            URL url = getIconUrl(key, SELECTED);

            try {
                selectedIcons.put(key, new ImageIcon(url));
            }
            catch (Exception e) {
                log.warn("Selected icon [" + key + "] not found");
                log.debug("Error loading selected icon [" + url + "]", e);
            }
        }

        return (Icon) selectedIcons.get(key);
    }

    /**
     * Returns a URL for the specified icon type associated with the specified
     * key.
     * 
     * @param key
     *            a key to search for an associated icon
     * @param iconType
     *            the type of icon to return
     * @return a URL specifying the location of an icon, or null if no
     *         associated icon found
     * @see DefaultIconSet#DEFAULT
     * @see DefaultIconSet#ROLLOVER
     * @see DefaultIconSet#DISABLED
     * @see DefaultIconSet#PRESSED
     * @see DefaultIconSet#SELECTED
     */
    private URL getIconUrl(final String key, final String iconType) {
        NodeList iconNodes = document.getDocumentElement()
                .getElementsByTagName("icon");

        for (int i = 0; i < iconNodes.getLength(); i++) {

            if (key.equals(iconNodes.item(i).getAttributes().getNamedItem("id")
                    .getNodeValue())) {

                Node iconNode = iconNodes.item(i);

                for (int j = 0; j < iconNode.getChildNodes().getLength(); j++) {

                    Node iconTypeNode = iconNode.getChildNodes().item(j);

                    if (iconType.equals(iconTypeNode.getNodeName())
                            && iconTypeNode.getFirstChild() != null) {

                    return getClass().getResource(
                            iconTypeNode.getFirstChild().getNodeValue()); }
                }
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.icon.IconSet#isPressedIconGenerated()
     */
    public final boolean isPressedIconGenerated() {
        return pressedIconGenerated;
    }

    /**
     * Sets whether pressed icons are generated where a pressed icon is not
     * found.
     * 
     * @param pressedIconGenerated
     *            The pressedIconGenerated to set.
     */
    public final void setPressedIconGenerated(final boolean pressedIconGenerated) {
        this.pressedIconGenerated = pressedIconGenerated;
    }

    /**
     * Generates a pressed icon corresponding to the specified icon.
     * 
     * @param icon
     *            an icon to generate a pressed instance of
     * @return an Icon, or null if the specified icon is null
     */
    private Icon generatePressedIcon(final Icon icon) {

        Icon pressedIcon = null;

        if (icon != null) {
            BufferedImage bim = new BufferedImage(icon.getIconWidth(), icon
                    .getIconHeight(), BufferedImage.TYPE_INT_ARGB);

            bim.getGraphics().drawImage(((ImageIcon) icon).getImage(), 1, 1,
                    null);

            pressedIcon = new ImageIcon(bim);
        }

        return pressedIcon;
    }
}