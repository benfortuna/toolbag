/*
 * $Id: ConfigPane.java [08-Nov-2003 12:35:51]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.config;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.fortuna.toolbag.ui.ButtonFactory;
import net.fortuna.toolbag.ui.option.CancelOption;
import net.fortuna.toolbag.ui.option.OkayOption;
import net.fortuna.toolbag.ui.option.OptionPane;
import net.fortuna.toolbag.ui.util.WindowUtils;

/**
 * Displays module configuration in default view. Used to modify existing
 * profiles.
 * 
 * @author Ben Fortuna
 */
public class ConfigPane extends JPanel implements OptionPane {

    private Config config;

    private JDialog dialog;

    private int option;

    public ConfigPane(Config config) {

        super(new BorderLayout());

        this.config = config;

        initTabs();
        initButtons();
    }

    private void initTabs() {

        if (config.getPages().size() > 1) {

            JTabbedPane tabs = new JTabbedPane();

            for (Iterator i = config.getPages().iterator(); i.hasNext();) {

                Component c = (Component) i.next();

                tabs.add(c, c.getName());
            }

            add(tabs, BorderLayout.CENTER);
        }
        else if (config.getPages().size() > 0) {

            add((Component) config.getPages().get(0), BorderLayout.CENTER);
        }
    }

    private void initButtons() {

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttons.add(ButtonFactory.getInstance().createButton(
                new OkayOption(this)));
        buttons.add(ButtonFactory.getInstance().createButton(
                new CancelOption(this)));

        add(buttons, BorderLayout.SOUTH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bfore.util.option.OptionPane#getOption()
     */
    public int getOption() {

        return option;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bfore.util.option.OptionPane#setOption(int)
     */
    public void setOption(int option) {

        this.option = option;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bfore.util.option.OptionPane#selectionMade()
     */
    public void selectionMade() {

        dialog.dispose();
    }

    public int showDialog(Frame parent, String title) {

        dialog = WindowUtils.createDialog(parent, this, title, true);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.show();

        return getOption();
    }
}