/*
 * Created on 25-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import net.fortuna.toolbag.ui.StyleFactory;

/**
 * @author Ben Fortuna
 *  
 */
public abstract class AbstractCellRenderer extends JTextPane implements
        TableCellRenderer {

    protected static final int TITLE_FONT_SIZE = 12;

    protected static final int DEFAULT_FONT_SIZE = 10;

    private boolean antialias;

    public AbstractCellRenderer() {

        this(false);
    }

    public AbstractCellRenderer(boolean antialias) {

        this.antialias = antialias;

        setDocument(StyleFactory.getInstance().createDocument());
        setEditable(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object object,
            boolean selected, boolean hasFocus, int row, int column) {

        if (selected) {

            setBackground(Color.RED);
        }
        else {

            setBackground(Color.WHITE);
        }

        try {

            getDocument().remove(0, getDocument().getLength());

            render(object);
        }
        catch (BadLocationException ble) {

            ble.printStackTrace();
        }

        return this;
    }

    protected abstract void render(Object object) throws BadLocationException;

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    protected void paintComponent(Graphics g) {

        /*
         * Graphics2D g2 = (Graphics2D) g; Dimension d = getSize();
         * 
         * GradientPaint gp = new
         * GradientPaint(d.width/2,0,Color.BLUE,d.width/2,d.height,Color.YELLOW);
         * 
         * g2.setPaint(gp); g2.setStroke(new BasicStroke(3));
         * 
         * Insets insets = getInsets();
         * 
         * g2.fillRect(1,1,getWidth()-1,getHeight()-1);
         * 
         * if (selected) {
         * 
         * g2.drawRect(0,0,getWidth(),getHeight()); }
         */

        if (antialias) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g2d);
            g2d.dispose();
        }
        else {
            super.paintComponent(g);
        }
    }
}