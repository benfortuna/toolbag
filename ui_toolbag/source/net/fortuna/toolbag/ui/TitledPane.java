/*
 * $Id: TitledPane.java [06-Oct-2003 19:54:25]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Provides a pane with a title
 * 
 * Consists of:
 *  - title bar - content pane - button pane
 * @author Ben Fortuna
 */
public abstract class TitledPane extends JPanel {

    private JTextPane titleBar;

    private JPanel contentPane;

    //private JPanel buttonPane;

    public TitledPane() {

        super(new BorderLayout());

        add(getTitleBar(), BorderLayout.NORTH);
        add(getContentPane(), BorderLayout.CENTER);
        //add(getButtonPane(),BorderLayout.SOUTH);
    }

    /**
     * @return
     */
    public JPanel getContentPane() {

        if (contentPane == null) {

            contentPane = new JPanel(new BorderLayout());
        }

        return contentPane;
    }

    /**
     * @return
     */
    public JTextPane getTitleBar() {

        if (titleBar == null) {

            Style titleStyle = StyleFactory.getInstance().addStyle("title");
            StyleConstants.setFontSize(titleStyle, 18);
            StyleConstants.setBold(titleStyle, true);
            StyleConstants.setForeground(titleStyle, Color.WHITE);

            StyledDocument document = StyleFactory.getInstance()
                    .createDocument();

            titleBar = new JTextPane(document) {

                /*
                 * (non-Javadoc)
                 * 
                 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
                 */
                protected void paintComponent(Graphics g) {

                    ((Graphics2D) g).setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    super.paintComponent(g);
                }

            };

            titleBar.setEditable(false);
            titleBar.setOpaque(true);
            titleBar.setBackground(Color.GRAY);
        }

        return titleBar;
    }

    /**
     * @return 
     * public JPanel getButtonPane() {
     * 
     * if (buttonPane == null) {
     * 
     * buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING)); }
     * 
     * return buttonPane; }
     */

}