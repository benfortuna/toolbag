/*
 * $Id: Table.java [15-Nov-2003 13:42:33]
 * 
 * Copyright (c) 2003 b.fore Solutions
 */
package net.fortuna.toolbag.ui.table;

import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * A view-based table implementation
 * 
 * @author Ben Fortuna
 */
public class Table extends JTable {

    private AbstractTableView view;

    private DoubleClick doubleClick;

    private RightClick rightClick;

    /**
     * Default constructor.
     */
    public Table() {
        this(null);
    }
    
    /**
     * Constructs a new table with the specified view.
     * @param view the default view
     */
    public Table(final AbstractTableView view) {
        TableCellRenderer r = getTableHeader().getDefaultRenderer();

        ((JLabel) r).setHorizontalAlignment(JLabel.LEFT);

        setView(view);

        doubleClick = new DoubleClick();

        addMouseListener(doubleClick);

        rightClick = new RightClick();

        addMouseListener(rightClick);
    }

    /**
     * @return
     */
    public AbstractTableView getView() {

        return view;
    }

    /**
     * @param view
     */
    public void setView(AbstractTableView view) {
        this.view = view;

        if (view != null) {
            setModel(view.getModel());

            if (view.getRowHeight() >= 0) {
                setRowHeight(view.getRowHeight());
            }

            setShowHorizontalLines(view.isHorizontalLinesVisible());
            setShowVerticalLines(view.isVerticalLinesVisible());

            if (view.getGridColour() != null) {
                setGridColor(view.getGridColour());
            }

            for (Iterator i = view.getRenderers().keySet().iterator(); i.hasNext();) {
                Class clazz = (Class) i.next();

                setDefaultRenderer(clazz, (TableCellRenderer) view.getRenderers()
                        .get(clazz));
            }
        }

        refresh();
    }

    /**
     * Refresh the current table view
     */
    public void refresh() {
        revalidate();
        repaint();
    }

    public DoubleClick getDoubleClick() {
        return doubleClick;
    }

    /**
     * @return
     */
    public RightClick getRightClick() {
        return rightClick;
    }

}