/*
 * Created on 19-Aug-2003
 * 
 * Copyright 2003.
 */
package net.fortuna.toolbag.ui.config;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.fortuna.toolbag.ui.StyleFactory;
import net.fortuna.toolbag.ui.option.CancelOption;
import net.fortuna.toolbag.ui.option.FinishOption;
import net.fortuna.toolbag.ui.option.OptionPane;
import net.fortuna.toolbag.ui.util.WindowUtils;

/**
 * @author Ben Fortuna
 *  
 */
public abstract class WizardPane extends JPanel implements OptionPane,
        ChangeListener, ActionListener {

    private JTextPane titleBar;

    private JPanel mainPane;

    private JPanel buttonPane;

    private JButton previousButton;

    private JButton nextButton;

    private Action finishAction;

    private Action cancelAction;

    private JDialog dialog;

    private int option;

    /**
     * Constructor.
     */
    public WizardPane() {

        super(new BorderLayout());

        previousButton = new JButton("Previous");
        previousButton.addActionListener(this);
        
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        
        add(getTitleBar(), BorderLayout.NORTH);
        add(getMainPane(), BorderLayout.CENTER);
        add(getButtonPane(), BorderLayout.SOUTH);
    }

    /**
     * @return
     */
    private JTextPane getTitleBar() {

        if (titleBar == null) {

            Style titleStyle = StyleFactory.getInstance().addStyle("title");
            StyleConstants.setFontSize(titleStyle, 16);
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
                protected final void paintComponent(final Graphics g) {

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

    protected final void setTitle(final String title) {

        try {

            Document document = getTitleBar().getDocument();

            document.remove(0, document.getLength());

            document.insertString(document.getLength(), title, StyleFactory
                    .getInstance().getStyle("title"));
        }
        catch (BadLocationException ble) {

            ble.printStackTrace();
        }
    }

    protected final void refresh() {

        Component[] pages = getMainPane().getComponents();

        for (int i = 0; i < pages.length; i++) {

            if (pages[i].isShowing()) {

                setTitle(pages[i].getName());
            }
        }

        // update button state..
        previousButton.setEnabled(!isFirst());
        nextButton.setEnabled(!isLast());
        getFinishAction().setEnabled(isFinishable());
    }

    /**
     * @return
     */
    protected final JPanel getButtonPane() {

        if (buttonPane == null) {

            buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            buttonPane.add(previousButton);
            buttonPane.add(nextButton);
            buttonPane.add(new JButton(getFinishAction()));
            buttonPane.add(new JButton(getCancelAction()));
        }

        return buttonPane;
    }

    /**
     * @return
     */
    protected final JPanel getMainPane() {

        if (mainPane == null) {

            mainPane = new JPanel(new CardLayout());
        }

        return mainPane;
    }

    /**
     * @return
     */
    public final Action getCancelAction() {

        if (cancelAction == null) {

            /*
             * cancelAction = new AbstractAction("Cancel") {
             * 
             * public void actionPerformed(ActionEvent e) {
             * 
             * dialog.dispose(); } };
             */
            cancelAction = new CancelOption(this);
        }

        return cancelAction;
    }

    /**
     * @return
     */
    public final Action getFinishAction() {

        if (finishAction == null) {

            /*
             * finishAction = new AbstractAction("Finish") {
             * 
             * public void actionPerformed(ActionEvent e) {
             * 
             * System.exit(0); } };
             */
            finishAction = new FinishOption(this);
        }

        return finishAction;
    }

    /**
     * @return indicates whether the first screen is currently displayed
     */
    protected abstract boolean isFirst();

    /**
     * @return indicates whether the last screen is currently displayed
     */
    protected abstract boolean isLast();

    /**
     * @return indicates whether the wizard may be finished
     */
    protected abstract boolean isFinishable();

    /**
     * Displays a dialog containing this wizard pane, with the specified parent
     * frame.
     * 
     * @param parent
     *            the parent of the dialog to display
     * @return a dialog option selection
     */
    public final int showDialog(final Frame parent) {

        dialog = WindowUtils.createDialog(parent, this,
                "touchbase - New Profile", true);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.show();

        return option;
    }

    /**
     * @see com.bfore.util.option.OptionPane#setOption(int)
     */
    public final void setOption(final int option) {

        this.option = option;
    }

    /**
     * @see com.bfore.util.option.OptionPane#getOption()
     */
    public final int getOption() {

        return option;
    }

    /**
     * @see com.bfore.util.option.OptionPane#selectionMade()
     */
    public final void selectionMade() {

        dialog.dispose();
    }

    /**
     * @see javax.swing.event.ChangeListener
     * #stateChanged(javax.swing.event.ChangeEvent)
     */
    public final void stateChanged(final ChangeEvent e) {

        getFinishAction().setEnabled(isFinishable());
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == previousButton) {
            ((CardLayout) getMainPane().getLayout()).previous(getMainPane());
        }
        else if (e.getSource() == nextButton) {
            ((CardLayout) getMainPane().getLayout()).next(getMainPane());
        }

        refresh();
    }
}