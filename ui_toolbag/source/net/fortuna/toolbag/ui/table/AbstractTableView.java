/*
 * $Id: AbstractTableView.java [15-Nov-2003 13:17:07]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.table;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.TableCellRenderer;

/**
 * Defines a table view consisting of a model and a renderer.
 * 
 * @author Ben Fortuna
 */
public abstract class AbstractTableView implements TableView {

    private ObjectTableModel model;

    private Map renderers;

    // cell properties..
    private int rowHeight;

    private boolean horizontalLinesVisible = true;

    private boolean verticalLinesVisible = true;

    private Color gridColour;

    public AbstractTableView(ObjectTableModel model, TableCellRenderer renderer) {
        this(model, renderer, -1);
    }

    public AbstractTableView(ObjectTableModel model, TableCellRenderer renderer,
            int rowHeight) {
        this.model = model;
        this.rowHeight = rowHeight;

        renderers = new HashMap();
        renderers.put(Object.class, renderer);
    }

    public AbstractTableView(ObjectTableModel model, Map renderers) {
        this(model, renderers, -1);
    }

    public AbstractTableView(ObjectTableModel model, Map renderers, int rowHeight) {
        this.model = model;
        this.renderers = renderers;
        this.rowHeight = rowHeight;
    }

    public String toString() {
        return getName();
    }
    
    /**
     * @return Returns the horizontalLinesVisible.
     */
    public boolean isHorizontalLinesVisible() {
        return horizontalLinesVisible;
    }
    
    /**
     * @param horizontalLinesVisible The horizontalLinesVisible to set.
     */
    public void setHorizontalLinesVisible(boolean horizontalLinesVisible) {
        this.horizontalLinesVisible = horizontalLinesVisible;
    }
    
    /**
     * @return Returns the model.
     */
    public ObjectTableModel getModel() {
        return model;
    }
    
    /**
     * @param model The model to set.
     */
    public void setModel(ObjectTableModel model) {
        this.model = model;
    }
    
    /**
     * @return Returns the rowHeight.
     */
    public int getRowHeight() {
        return rowHeight;
    }
    
    /**
     * @param rowHeight The rowHeight to set.
     */
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }
    
    /**
     * @return Returns the verticalLinesVisible.
     */
    public boolean isVerticalLinesVisible() {
        return verticalLinesVisible;
    }
    
    /**
     * @param verticalLinesVisible The verticalLinesVisible to set.
     */
    public void setVerticalLinesVisible(boolean verticalLinesVisible) {
        this.verticalLinesVisible = verticalLinesVisible;
    }
    
    /**
     * @return Returns the renderers.
     */
    public Map getRenderers() {
        return renderers;
    }
    
    /**
     * @return Returns the gridColour.
     */
    public Color getGridColour() {
        return gridColour;
    }
    
    /**
     * @param gridColour The gridColour to set.
     */
    public void setGridColour(Color gridColour) {
        this.gridColour = gridColour;
    }
}