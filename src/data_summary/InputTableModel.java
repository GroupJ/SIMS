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
public class InputTableModel extends DefaultTableModel  {

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

        types.add(String.class);

        titles.add("File Name");

        editable.add(new Boolean(false));
    }

    /**
     * Creates an empty SummaryTableModel
     */
    protected InputTableModel()   {
        resetDefault();
    }

    /**
     * Add a row entry into the table model.
     * @param arg0 Datahouse default parameters
     * @param arg1 Datahouse R0 to Rn entries
     */
    protected void addRow(String name, int RTotal)    {

        String sampleName = name.substring(0, name.indexOf("@"));
        System.err.println(sampleName);

        int index = -1;
        for (int i = 0; i < tableEntries.size(); i++)   {
            if (sampleName.compareTo((String)tableEntries.get(i).get(0)) == 0)    {
                index = i;
                break;
            }
        }

        if (index == -1)    {
            System.err.println("Adding");
            tableEntries.add(new ArrayList<Object> ());
            tableEntries.get(tableEntries.size() - 1).add(sampleName);

            int totCol = getColumnCount();
            for (int i = 0; i < totCol; i++)
                tableEntries.get(tableEntries.size() - 1).add(null);
        }

        int totalCol = getColumnCount();

        System.err.println(totalCol + " " + RTotal + " " + getRowCount());

        for (int i = ((totalCol -1)/2); i < RTotal; i++)    {
            addColumn("R" + (i) + " (MV)", String.class, true);
            addColumn("R" + (i) + " (StdErr)", String.class, true);
        }

        System.err.println(types.size() + " " + getColumnCount() + " " + RTotal + " " + getRowCount());
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
        editable.set(col, new Boolean(value));
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

    public Object getValueAt(int r, int c)   {
        return tableEntries.get(r).get(c);
    }

    public void setValueAt(Object value, int row, int col)  {

        if (!isDouble(value) && ((String)value).length() != 0)
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Not double value.",
                    "Incorrect Type",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        else
            tableEntries.get(row).set(col, value);
    }

    private boolean isDouble(Object value)    {
        try {
            Double.parseDouble(value.toString());
        } catch (Exception e)   {
            return false;
        }
        return true;
    }

    public int getColumnCount() {
        if (types == null)
            return 0;
        return types.size();
    }

    public int getRowCount()    {
        if (tableEntries == null)
            return 0;
        return tableEntries.size();
    }

    public Class getColumnClass(int columnIndex) {
        return types.get(columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable.get(columnIndex).booleanValue();
    }

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
        this.editable.add(new Boolean(editable));

        for (int i = 0; i < rowSize; i++)   {
            this.tableEntries.get(i).add(null);
        }
    }
}
