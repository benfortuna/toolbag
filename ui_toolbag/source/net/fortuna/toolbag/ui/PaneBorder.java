/*
 * $Id: PaneBorder.java [01-Oct-2003 23:32:57]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Defines the eight (8) panes which make up a pane border
 * 
 * @author Ben Fortuna
 */
public class PaneBorder {

    public static final String GREY_THEME = "grey";

    //private static final String THEME = "banded";
    //private static final String THEME = "iframe";
    //private static final String THEME = "lucid";
    //private static final String THEME = "page";
    //private static final String THEME = "round";
    //private static final String THEME = "shadow";
    //private static final String THEME = "shadowBR";
    //private static final String THEME = "triangle";

    public JPanel topPane;

    public JPanel leftPane;

    public JPanel rightPane;

    public JPanel bottomPane;

    public PaneBorder(Image topLeft, Image top, Image topRight, Image left,
            Image right, Image bottomLeft, Image bottom, Image bottomRight) {

        topPane = new JPanel(new BorderLayout());
        topPane.add(new ImagePane(topLeft), BorderLayout.WEST);
        topPane.add(new TexturePane(top, TexturePane.HORIZONTAL),
                BorderLayout.CENTER);
        topPane.add(new ImagePane(topRight), BorderLayout.EAST);

        leftPane = new TexturePane(left, TexturePane.VERTICAL);

        rightPane = new TexturePane(right, TexturePane.VERTICAL);

        bottomPane = new JPanel(new BorderLayout());
        bottomPane.add(new ImagePane(bottomLeft), BorderLayout.WEST);
        bottomPane.add(new TexturePane(bottom, TexturePane.HORIZONTAL),
                BorderLayout.CENTER);
        bottomPane.add(new ImagePane(bottomRight), BorderLayout.EAST);
    }
}