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

import java.awt.Color;
import java.util.Map;

/**
 * @author benfortuna
 *
 * Defines a view that may be applied to a table. A view consists
 * of one or more renderers, a table model, and other settings.
 */
public interface TableView {

    /**
     * Returns a displayable name for the view. 
     * @return a string
     */
    String getName();
    
    /**
     * Returns the table model used by the view.
     * @return a table model
     */
    ObjectTableModel getModel();
    
    /**
     * Returns a map of renderers for the view using class
     * type as a key. At the very least there should be a
     * renderer associated with <code>Object.class</code>
     * @return a map of renderers.
     */
    Map getRenderers();
    
    /**
     * Returns the height of table rows for this view.
     * @return the pixel height of rows
     */
    int getRowHeight();
    
    /**
     * Returns the colour of grid lines for this view.
     * @return the grid line colour
     */
    Color getGridColour();
    
    /**
     * Indicates whether horizontal grid lines are visible.
     * @return true if horizontal grid lines are visible
     */
    boolean isHorizontalLinesVisible();
    
    /**
     * Indicates whether vertical grid lines are visible.
     * @return true if vertical grid lines are visible
     */
    boolean isVerticalLinesVisible();
}
