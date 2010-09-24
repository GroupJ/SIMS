/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 *
 * @author 20378332
 */
public final class SummaryTableModel extends DefaultTableModel    {

    private static final int defaultColTotal = 8;
    // column data types
    private ArrayList<Class> types;
    // column editable ?
    private ArrayList<Boolean> editable;
    // 2D grid of table data
    private ArrayList<ArrayList<Object>> tableEntries;
    // Column titles
    private ArrayList<String> titles;

    /**
     * Resets table to default. ie. Clears the table
     */
    protected void resetDefault()  {
        types = new ArrayList<Class>();
        editable = new ArrayList<Boolean>();
        tableEntries = new ArrayList<ArrayList<Object>> ();
        titles = new ArrayList<String> ();

        types.add(Integer.class);
        types.add(Boolean.class);
        types.add(Boolean.class);
        types.add(String.class);
        types.add(String.class);
        types.add(String.class);
        types.add(String.class);
        types.add(String.class);

        titles.add("File #");
        titles.add("STD");
        titles.add("USE");
        titles.add("File Name");
        titles.add("X");
        titles.add("Y");
        titles.add("PC-Start");
        titles.add("PC-End");

        editable.add(false);
        editable.add(true);
        editable.add(true);
        editable.add(false);
        editable.add(false);
        editable.add(false);
        editable.add(false);
        editable.add(false);

    }

    /**
     * Creates an empty SummaryTableModel
     */
    protected SummaryTableModel()   {
        resetDefault();
    }

    /**
     * Add a row entry into the table model.
     * @param arg0 Datahouse default parameters
     * @param arg1 Datahouse R0 to Rn entries
     */
    protected void addRow(String[] arg0, String[] arg1)    {
        
        int numRn = arg1.length/2;
        int colSize = getColumnCount();

        for (int i = 0; i < numRn - ((colSize - defaultColTotal)/2); i++) {
            addColumn("R" + ((((colSize - defaultColTotal)/2)) + i) + " (MV)", String.class, false);
            addColumn("R" + ((((colSize - defaultColTotal)/2)) + i) + " (Std Err(%))", String.class, false);
        }

        ArrayList<Object> row = new ArrayList<Object> ();
        row.add(new Integer(getRowCount() + 1));
        row.add(false);
        row.add(true);

        for (int i = 0; i < arg0.length; i++)
            row.add(arg0[i]);

        for (int i = 0; i < numRn * 2; i += 2) {
            row.add(arg1[i]);
            row.add(arg1[i+1]);
        }

        for (int i = row.size(); i < colSize; i++)
            row.add(null);

        tableEntries.add(row);
    }

    /**
     * Returns the title of the column.
     * @param col - the col index starting from 0
     * @return String title of the column
     */
    protected String getColumnTitle(int col) {
        return titles.get(col);
    }

    /**
     * Sets a cell position as editable based on 'value'.
     * Current implementation sets the entire column rather than
     * cell.
     * @param row
     * @param col
     * @param value
     */
    protected void setEditable(int row, int col, boolean value)   {
        editable.set(col, value);
    }

    protected void removeRow(String fileName)   {
        int index = -1;
        int rows = getRowCount();
        for (int i = 0; i < rows; i++)
            if (fileName.compareTo((String)tableEntries.get(i).get(3)) == 0)
                index = i;

        if (index != -1)
            tableEntries.remove(index);
    }

    @Override
    public Object getValueAt(int r, int c)   {
        return tableEntries.get(r).get(c);
    }

    public void setValueAt(Object value, int row, int col)  {
        tableEntries.get(row).set(col, value);
    }

    @Override
    public int getColumnCount() {
        if (types == null)
            return 0;
        return types.size();
    }

    @Override
    public int getRowCount()    {
        if (tableEntries == null)
            return 0;
        return tableEntries.size();
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types.get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable.get(columnIndex).booleanValue();
    }

    @Override
    public String getColumnName(int col)    {
        return titles.get(col);
    }

    /*
     * Adds a column to the table
     */
    private void addColumn(String name, Class type, boolean editable)  {
        int rowSize = getRowCount();
        titles.add(name);
        types.add(type);
        this.editable.add(editable);

        for (int i = 0; i < rowSize; i++)   {
            this.tableEntries.get(i).add(null);
        }
    }

}
