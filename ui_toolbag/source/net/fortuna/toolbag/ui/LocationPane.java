/*
 * $Id: FileSelectionPane.java [09-Sep-2003 00:54:48]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.fortuna.toolbag.ui.util.LayoutUtils;
import net.fortuna.toolbag.ui.util.WindowUtils;

/**
 * Provides a method of selecting a file by either entering a url/filename or
 * browsing the filesystem.
 * 
 * @author Ben Fortuna
 */
public class LocationPane extends JPanel implements ActionListener {

    private List history;

    private JFileChooser chooser;

    private JComboBox locationCombo;

    private JButton browseButton;
    
    private ChangeEvent changeEvent;

    public LocationPane() {
        super(new GridBagLayout());

        locationCombo = new JComboBox(new Vector(getHistory())) {
            /**
             * @see javax.swing.JComponent#getPreferredSize()
             */
            public Dimension getPreferredSize() {
                return new Dimension(195, super.getPreferredSize().height);
            }
        };

        locationCombo.setEditable(true);
        locationCombo.addActionListener(this);

        browseButton = new JButton(new BrowseAction());

        add(locationCombo, LayoutUtils.getConstraints(0, 0, 1, 1, 0, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.HORIZONTAL, 0,
                0, new Insets(0, 0, 0, 2)));
        add(browseButton, LayoutUtils.getConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 0, 0,
                new Insets(0, 2, 0, 0)));
    }

    public JFileChooser getChooser() {
        if (chooser == null) {
            chooser = new JFileChooser();
        }

        return chooser;
    }

    public List getHistory() {
        if (history == null) {
            history = new ArrayList();
        }

        return history;
    }

    public URL getUrl() {
        return (URL) locationCombo.getSelectedItem();
    }
    
    /**
     * @return
     */
    public File getFile() {
        return new File(getUrl().getFile());
    }

    /**
     * @param file
     */
    public void setSelectedUrl(URL url) {
        if (!getHistory().contains(url)) {
            getHistory().add(url);

            locationCombo.addItem(url);
        }

        locationCombo.setSelectedItem(url);

        fireStateChanged();
    }
    
    public void setSelectedFile(File file) {
        try {
            setSelectedUrl(file.toURL());
        }
        catch (MalformedURLException mue) {
        }
    }

    public void setEnabled(boolean enabled) {
        locationCombo.setEnabled(enabled);
        browseButton.setEnabled(enabled);
    }

    /**
     * @see javax.swing.JComponent#setToolTipText(java.lang.String)
     */
    public void setToolTipText(String text) {
        locationCombo.setToolTipText(text);
    }

    private class BrowseAction extends AbstractAction {

        public BrowseAction() {
            super("Browse");
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {

            int selection = getChooser().showOpenDialog(
                    (Component) e.getSource());

            if (selection == JFileChooser.APPROVE_OPTION) {
                setSelectedFile(getChooser().getSelectedFile());
            }
        }
    }

    public static void main(String[] args) {

        JDialog dialog = WindowUtils.createDialog((Frame) null,
                new LocationPane(), "Select a file..", false);
        dialog.pack();
        dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        WindowUtils.center(dialog);

        dialog.setVisible(true);
    }

    public void addChangeListener(ChangeListener listener) {
        listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        listenerList.remove(ChangeListener.class, listener);
    }

    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                // Lazily create the event:
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }

                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        URL url = null;
        
        try {
            url = new URL(locationCombo.getSelectedItem().toString());
        }
        catch (MalformedURLException mue) {
            
            try {
                url = new File(locationCombo.getSelectedItem().toString()).toURL();
            }
            catch (MalformedURLException mue2) {
            }
        }
        
        if (url != null) {
            setSelectedUrl(url);
        }
    }
}