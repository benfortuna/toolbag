/*
 * Created on 16/11/2004
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
package net.fortuna.toolbag.ui.table;

import javax.swing.table.TableModel;

import net.fortuna.toolbag.collection.Filter;
import net.fortuna.toolbag.collection.Sorter;

/**
 * @author benfortuna
 *
 * Extends from the TableModel definition to associate a user
 * object with each table row.
 */
public interface ObjectTableModel extends TableModel {

    /**
     * Returns the user object associated with the specified
     * row.
     * @param row the row associated with the returned object
     * @return an associated user object or null if no object
     * associated
     */
    Object getUserObject(int row);
    
    /**
     * Applies the specified sorting to the table model.
     * @param sorter a sorter used to sort the model
     */
    void setSorter(Sorter sorter);
        
    /**
     * Returns the currently applied sorter.
     * @return a sorter or null if no sorting applied
     */
    Sorter getSorter();
    
    /**
     * Applies the specified rules to the table model.
     * @param filters an array of rules used to filter the model
     */
    void setFilter(Filter filter);
    
    /**
     * Returns the currently applied filter.
     * @return a filter or null if no filter applied
     */
    Filter getFilter();
}
