/*
 * $Id: OptionPane.java [07-Dec-2003 14:41:50]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.option;

/**
 * Options used in dialogs/frames to indicate user selection
 * 
 * @author Ben Fortuna
 */
public interface OptionPane {

    int OPEN_OPTION = 1;

    int CANCEL_OPTION = 2;

    int OKAY_OPTION = 3;

    int FINISH_OPTION = 4;

    void setOption(int option);

    int getOption();

    void selectionMade();
}