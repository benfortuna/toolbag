/*
 * $Id: ApplicationPane.java [03-Oct-2003 19:47:02]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.fortuna.toolbag.ui.icon.AbstractIconSetFactory;
import net.fortuna.toolbag.ui.icon.DefaultIconSet;
import net.fortuna.toolbag.ui.util.LayoutUtils;

/**
 * Base class for an application pane.
 * 
 * Consists of:
 *  - tool bar - title bar - content pane - status bar
 * @author Ben Fortuna
 */
public abstract class ApplicationPane extends JPanel {

    private JMenuBar menuBar;

    private JToolBar toolBar;

    private JTextPane titleBar;

    private JPanel contentPane;

    private JTextField statusBar;

    private JProgressBar progressBar;

    public ApplicationPane() {

        super(new BorderLayout());

        JPanel statusPane = new JPanel(new GridBagLayout());
        statusPane.add(getStatusBar(), LayoutUtils.getConstraints(0, 0, 1, 1,
                1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                0, 0, new Insets(2, 2, 2, 2)));
        statusPane.add(getProgressBar(), LayoutUtils.getConstraints(1, 0, 1, 1,
                0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0, 0,
                new Insets(2, 2, 2, 2)));

        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(getTitleBar(), BorderLayout.NORTH);
        mainPane.add(getContentPane(), BorderLayout.CENTER);
        mainPane.add(statusPane, BorderLayout.SOUTH);

        add(getToolBar(), BorderLayout.NORTH);
        add(mainPane, BorderLayout.CENTER);
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
    public JTextField getStatusBar() {

        if (statusBar == null) {

            statusBar = new JTextField();
            statusBar.setEditable(false);
        }

        return statusBar;
    }

    /**
     * @return
     */
    public JProgressBar getProgressBar() {

        if (progressBar == null) {

            progressBar = new JProgressBar();
        }

        return progressBar;
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

            //Image image =
            // Toolkit.getDefaultToolkit().getImage("d:/images/icons/bookmarks.png");

            Style s = StyleFactory.getInstance().addStyle("icon");
            //StyleConstants.setIcon(s,new
            // ImageIcon(image.getScaledInstance(-1,20,Image.SCALE_DEFAULT)));
            StyleConstants.setIcon(s, AbstractIconSetFactory.getDefaultInstance().getDefaultIconSet().getIcon(
                    DefaultIconSet.BOOKMARK));
            StyleConstants.setSpaceAbove(s, 5);
            StyleConstants.setSpaceBelow(s, 0);

            StyledDocument document = StyleFactory.getInstance()
                    .createDocument();

            titleBar = new JTextPane(document) {

                /*
                 * (non-Javadoc)
                 * 
                 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
                 */
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    super.paintComponent(g2d);
                    g2d.dispose();
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
     */
    public JToolBar getToolBar() {

        if (toolBar == null) {

            toolBar = new JToolBar();
            //toolBar.putClientProperty(Options.HEADER_STYLE_KEY,HeaderStyle.BOTH);
            toolBar.setRollover(true);
        }

        return toolBar;
    }

    /**
     * @return
     */
    public JMenuBar getMenuBar() {

        if (menuBar == null) {

            menuBar = new JMenuBar();
            //menuBar.putClientProperty(Options.HEADER_STYLE_KEY,HeaderStyle.BOTH);
        }

        return menuBar;
    }

}