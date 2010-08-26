/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import javax.swing.table.DefaultTableModel;
import java.util.*;
import IO.*;

/**
 *
 * @author 20378332
 */
public class SummaryTableModel extends DefaultTableModel    {

    private ArrayList<Class> types;
    private ArrayList<Boolean> editable;
    private ArrayList<ArrayList<Object>> tableEntries;
    private ArrayList<String> titles;

    public void resetDefault()  {
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

        titles.add("File #");
        titles.add("STD");
        titles.add("USE");
        titles.add("File Name");
        titles.add("(X,Y)");
        titles.add("PC-Start");
        titles.add("PC-End");

        editable.add(new Boolean(false));
        editable.add(new Boolean(true));
        editable.add(new Boolean(true));
        editable.add(new Boolean(false));
        editable.add(new Boolean(false));
        editable.add(new Boolean(false));
        editable.add(new Boolean(false));

    }

    public SummaryTableModel()   {
        resetDefault();
    }

    public void addRow(Object[] input) {
        tableEntries.add(new ArrayList<Object> ());
        int size = tableEntries.size() - 1;
        for (int i = 0; i < input.length; i++)
            tableEntries.get(size).add(input[i]);
    }

    // adds new row full of nulls;
    public void addRow() {
        tableEntries.add(new ArrayList<Object> ());
        int size = tableEntries.size() - 1;
        int colSize = getColumnCount();
        for (int i = 0; i < colSize; i++)
            tableEntries.get(size).add(null);
    }

    public void addRow(DataHouse dh)    {

        System.err.println(getColumnCount());

        int numRn = dh.CR_row_title.size();
        int colSize = getColumnCount();

        System.err.println(numRn + " " + colSize);

        for (int i = 0; i < numRn - ((colSize - 7)/2); i++) {
            addColumn("R" + i + " (MV)", String.class, false);
            addColumn("R" + i + " (Std Err(%))", String.class, false);
        }

        ArrayList<Object> row = new ArrayList<Object> ();
        row.add(new Integer(getRowCount() + 1));
        row.add(new Boolean(false));
        row.add(new Boolean(false));
        row.add(dh.SampleName);
        row.add("(" + dh.x_pos + ", " + dh.y_pos + ")");
        row.add("" + dh.PR_start);
        row.add("" + dh.PR_end);

        for (int i = 0; i < numRn; i++) {
            row.add("" + dh.CR_Grid.get(i).get(0));
            row.add("" + dh.CR_Grid.get(i).get(2));
        }

        for (int i = row.size(); i < colSize; i++)
            row.add(null);

        tableEntries.add(row);

        System.err.println(getColumnCount());
    }

    public void setEditable(int row, int col, boolean value)   {
        editable.set(col, new Boolean(value));
    }

    public Object getValueAt(int r, int c)   {
        return tableEntries.get(r).get(c);
    }

    public void setValueAt(Object value, int row, int col)  {
        tableEntries.get(row).set(col, value);
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

    public void addColumn(String name, Class type, boolean editable)  {
        int colSize = getColumnCount();
        int rowSize = getRowCount();
        titles.add(name);
        types.add(type);
        this.editable.add(new Boolean(editable));

        for (int i = 0; i < rowSize; i++)   {
            this.tableEntries.get(i).add(null);
        }
    }

}
