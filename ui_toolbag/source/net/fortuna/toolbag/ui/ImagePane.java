/*
 * Created on 27-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Ben Fortuna
 *  
 */
public class ImagePane extends JPanel {

    private Image image;

    /**
     *  
     */
    public ImagePane(Image image) {

        this.image = image;

        loadImage(image);

        setPreferredSize(new Dimension(image.getWidth(null), image
                .getHeight(null)));

        setOpaque(false);
    }

    /**
     * @param arg0
     */
    public ImagePane(boolean isDoubleBuffered) {

        super(isDoubleBuffered);
    }

    /**
     * @param arg0
     */
    public ImagePane(LayoutManager layout) {

        super(layout);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ImagePane(LayoutManager layout, boolean isDoubleBuffered) {

        super(layout, isDoubleBuffered);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(Graphics g) {

        g.drawImage(image, 0, 0, null);

        super.paintComponent(g);
    }

    public void loadImage(Image image) {

        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);

        try {

            mt.waitForID(0);
        }
        catch (InterruptedException ie) {

            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Image image = Toolkit.getDefaultToolkit().getImage(
                "d:/images/icons/gynt-64x64.png");

        ImagePane pane = new ImagePane(image);

        JFrame f = new JFrame("ImagePane Test");
        f.getContentPane().add(pane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}