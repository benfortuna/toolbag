/*
 * Created on 27-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Ben Fortuna
 *  
 */
public class TexturePane extends JPanel {

    public static int HORIZONTAL = 0;

    public static int VERTICAL = 1;

    private Image image;

    private int alignment;

    /**
     *  
     */
    public TexturePane(Image image, int alignment) {

        this.image = image;
        this.alignment = alignment;

        loadImage(image);

        setPreferredSize(new Dimension(image.getWidth(null), image
                .getHeight(null)));

        setOpaque(false);
    }

    /**
     * @param arg0
     */
    public TexturePane(boolean isDoubleBuffered) {

        super(isDoubleBuffered);
    }

    /**
     * @param arg0
     */
    public TexturePane(LayoutManager layout) {

        super(layout);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TexturePane(LayoutManager layout, boolean isDoubleBuffered) {

        super(layout, isDoubleBuffered);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(Graphics g) {

        Dimension ib = new Dimension(image.getWidth(null), image
                .getHeight(null));

        BufferedImage bim = new BufferedImage(ib.width, ib.height,
                BufferedImage.TYPE_INT_RGB);
        bim.getGraphics().drawImage(image, 0, 0, ib.width, ib.height, null);

        TexturePaint paint = new TexturePaint(bim, new Rectangle(ib.width,
                ib.height));

        ((Graphics2D) g).setPaint(paint);

        if (alignment == VERTICAL) {

            g.fillRect(0, 0, ib.width, getHeight());
        }
        else {

            g.fillRect(0, 0, getWidth(), ib.height);
        }

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

        TexturePane pane = new TexturePane(image, HORIZONTAL);

        JFrame f = new JFrame("TexturePane Test");
        f.getContentPane().add(pane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}