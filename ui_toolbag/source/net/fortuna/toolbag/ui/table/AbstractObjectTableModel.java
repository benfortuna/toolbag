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

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.fortuna.toolbag.collection.Filter;
import net.fortuna.toolbag.collection.Sorter;

/**
 * @author benfortuna
 *
 * Abstract implementation of object table model. Provides support
 * for sorting and filtering.
 */
public abstract class AbstractObjectTableModel extends AbstractTableModel implements ObjectTableModel {

    private List userObjects;
    
    private List filtered;
    
    private Sorter sorter;
    
    private Filter filter;
    
    /**
     * Default constructor.
     */
    public AbstractObjectTableModel() {
        userObjects = new ArrayList();
    }
    
    /**
     * @param items The items to set.
     */
    protected final void setUserObjects(final List items) {
        this.userObjects = items;
        if (sorter != null) {
            sorter.sort(items);
        }
        if (filter != null) {
            filtered = (List) filter.filter(items);
        }
        else {
            filtered = null;
        }
    }
    /**
     * @return Returns the items.
     */
    protected final List getUserObjects() {
        if (filtered != null) {
            return filtered;
        }
        return userObjects;
    }

    /* (non-Javadoc)
     * @see net.fortuna.toolbag.ui.table.ObjectTableModel#getUserObject(int)
     */
    public final Object getUserObject(final int row) {
        return getUserObjects().get(row);
    }
    
    /**
     * @return Returns the filter.
     */
    public final Filter getFilter() {
        return filter;
    }
    /**
     * @param filter The filter to set.
     */
    public final void setFilter(final Filter filter) {
        if (filter != null) {
            filtered = (List) filter.filter(userObjects);
        }
        else {
            filtered = null;
        }
        this.filter = filter;
    }
    
    /**
     * @return Returns the sorter.
     */
    public final Sorter getSorter() {
        return sorter;
    }
    /**
     * @param sorter The sorter to set.
     */
    public final void setSorter(final Sorter sorter) {
        if (sorter != null) {
            sorter.sort(userObjects);
            if (filtered != null) {
                sorter.sort(filtered);
            }
        }
        this.sorter = sorter;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public final int getRowCount() {
        if (filtered != null) {
            return filtered.size();
        }
        return userObjects.size();
    }
}
