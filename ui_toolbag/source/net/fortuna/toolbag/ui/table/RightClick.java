/*
 * $Id: RightClick.java [28-Mar-2004 21:42:25]
 * 
 * Copyright (c) 2004 b.fore Solutions
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
 * fires an action event when component is right-clicked
 * 
 * @author Ben Fortuna
 */
public class RightClick implements MouseListener {

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

        if (e.isPopupTrigger()) {

            ActionEvent ae = new ActionEvent(e.getSource(),
                    ActionEvent.ACTION_PERFORMED, "right-click-" + e.getX()
                            + "x" + e.getY());

            Iterator i = actionListeners.iterator();

            while (i.hasNext()) {

                ((ActionListener) i.next()).actionPerformed(ae);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent e) {

        if (e.isPopupTrigger()) {

            ActionEvent ae = new ActionEvent(e.getSource(),
                    ActionEvent.ACTION_PERFORMED, "right-click-" + e.getX()
                            + "x" + e.getY());

            Iterator i = actionListeners.iterator();

            while (i.hasNext()) {

                ((ActionListener) i.next()).actionPerformed(ae);
            }
        }
    }
}