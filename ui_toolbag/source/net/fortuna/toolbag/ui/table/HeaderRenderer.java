/*
 * $Id: HeaderRenderer.java [04-Jan-2004 20:31:52]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Overrides default implementation to provide alignment of header columns.
 * 
 * @author Ben Fortuna
 */
public class HeaderRenderer extends DefaultTableCellRenderer {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        return c;
    }

}