/*
 * $Id: DoubleClick.java [06-Dec-2003 19:58:29]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fires an action event when component is double-clicked
 * 
 * @author Ben Fortuna
 */
public class DoubleClick implements MouseListener {

    private List actionListeners = new ArrayList();

    public void addActionListener(ActionListener listener) {

        actionListeners.add(listener);
    }

    public void removeActionListener(ActionListener listener) {

        actionListeners.remove(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            ActionEvent ae = new ActionEvent(e.getSource(),
                    ActionEvent.ACTION_PERFORMED, "double-click");

            Iterator i = actionListeners.iterator();

            while (i.hasNext()) {

                ((ActionListener) i.next()).actionPerformed(ae);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent e) {
    }
}