/*
 * $Id: PaneBorderFactory.java [01-Oct-2003 23:53:38]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton factory for creating pane borders
 * 
 * @author Ben Fortuna
 */
public class PaneBorderFactory {

    private static PaneBorderFactory instance = new PaneBorderFactory();

    private Map borders = new HashMap();

    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static PaneBorderFactory getInstance() {

        return instance;
    }

    /**
     * Constructor made private to enforce singleton
     */
    private PaneBorderFactory() {
    }

    public PaneBorder createPaneBorder(String theme) {

        if (borders.get(theme) == null) {

            Image topLeft = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme
                            + "/topLeft.gif"));
            Image top = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme + "/top.gif"));
            Image topRight = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme
                            + "/topRight.gif"));
            Image left = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme + "/left.gif"));
            Image right = toolkit
                    .getImage(ClassLoader.getSystemResource("images/border/"
                            + theme + "/right.gif"));
            Image bottomLeft = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme
                            + "/bottomLeft.gif"));
            Image bottom = toolkit
                    .getImage(ClassLoader.getSystemResource("images/border/"
                            + theme + "/bottom.gif"));
            Image bottomRight = toolkit.getImage(ClassLoader
                    .getSystemResource("images/border/" + theme
                            + "/bottomRight.gif"));

            borders.put(theme, new PaneBorder(topLeft, top, topRight, left,
                    right, bottomLeft, bottom, bottomRight));
        }

        return (PaneBorder) borders.get(theme);
    }
}