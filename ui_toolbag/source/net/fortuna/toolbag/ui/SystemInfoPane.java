/*
 * Created on 26/10/2004
 *
 * $Id$
 *
 * Copyright (c) 2004, Ben Fortuna
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * @author benfortuna
 *
 * Displays system properties of the Java Virtual Machine.
 */
public class SystemInfoPane extends JPanel {

    /**
     * Default constructor.
     */
    public SystemInfoPane() {
        super(new BorderLayout());
        /*
        DefaultTableModel model = new DefaultTableModel()
        {
            /* (non-Javadoc)
             * @see javax.swing.table.DefaultTableModel#getColumnName(int)
             *
            public String getColumnName(int column) {
                switch (column) {
                    case 0: return "Key";
                    case 1: return "Value";
                }
                return super.getColumnName(column);
            }
        };

        model.addColumn("Key", System.getProperties().keySet().toArray());
        model.addColumn("Value", System.getProperties().entrySet().toArray());
        /*
        for (Iterator i = System.getProperties().keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            model.addRow(new Object[] {key, System.getProperty(key)});
        }
        */
        TableModel model = new AbstractTableModel() {
            public int getColumnCount() {
                return 2;
            }

            public int getRowCount() {
                return System.getProperties().size();
            }

            public Object getValueAt(int row, int column) {
                List keys = new ArrayList(System.getProperties().keySet());
                
                Collections.sort(keys);
                
                if (column == 0) {
                    return keys.get(row);
                }
                else if (column == 1) {
                    return System.getProperty((String) keys.get(row));
                }

                return null;
            }
            
            /* (non-Javadoc)
             * @see javax.swing.table.AbstractTableModel#getColumnName(int)
             */
            public String getColumnName(int column) {
                switch (column) {
                    case 0: return "Property";
                    case 1: return "Value";
                }
                return super.getColumnName(column);
            }
        };
        
        JTable table = new JTable(model);

        JScrollPane scroller = new JScrollPane(table);
        
        add(scroller);
    }
}
