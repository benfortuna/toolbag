/*
 * $Id: DateRenderer.java [04-Jan-2004 19:58:43]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.table;

import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import net.fortuna.toolbag.format.InformalDateFormat;

/**
 * Renders a date-based table cell
 * 
 * @author Ben Fortuna
 */
public class DateRenderer extends JLabel implements TableCellRenderer {

    /*
     * (non-Javadoc)
     * 
     * @see com.bfore.util.table.AbstractCellRenderer#render(java.lang.Object)
     * 
     * protected void render(Object object) throws BadLocationException {
     * 
     * Date date = (Date) object;
     * 
     * if (date != null) {
     * getDocument().insertString(getDocument().getLength(),DateUtils.format(date),null); } }
     */

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value != null) {

            setText(InformalDateFormat.getInstance().format((Date) value));
        }
        else {

            setText(null);
        }

        if (isSelected) {

            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }
        else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        setEnabled(table.isEnabled());
        setFont(table.getFont());
        setOpaque(true);

        return this;
    }

}