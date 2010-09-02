/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

/**
 * A front end for Data Summary Sub System
 * @author 20378332
 */
public class DSFrontEnd {

    // variables are initialised in order so tablemodel has to come first
    protected static SummaryTableModel tableModel = new SummaryTableModel();
    private static DSWindow window = new DSWindow();

    /**
     * Resets everything to default.
     */
    public static void init()   {
        resetTable();
        resetWindowSettings();
        window.setVisible(true);
    }

    /**
     * Move window position and size to default.
     */
    public static void resetWindowSettings()    {
        window.setLocation(0, 600);
    }

    /**
     * Displays summary window.
     */
    public static void showWindow()  {
        window.setVisible(true);
    }

    /**
     * Hide summary window.
     */
    public static void hideWindow() {
        window.setVisible(false);
    }

    /**
     * Reset the contents of the summary table.
     */
    public static void resetTable() {
        tableModel.resetDefault();
        updateSummaryTable();
    }

    /**
     * Add a row entry into the summary table.
     * @param arg0 the common parameters of Datahouse
     * @param arg1 R0 to Rn of Datahouse
     */
    public static void addRow(String[] arg0, String[] arg1) {
        tableModel.addRow(arg0, arg1);
    }

    /**
     * Update the summary table. This is required to make
     * changes appear.
     */
    public static void updateSummaryTable()    {
        tableModel.fireTableDataChanged();
        tableModel.fireTableStructureChanged();
        window.resetColWidth();

        window.extendComboList((tableModel.getColumnCount() - 7) / 2);
        showWindow();
    }

    /**
     * Removes a row entry in the table based on the file name.
     * @param fileName the filename
     */
    public static void removeRow(String fileName)   {
        tableModel.removeRow(fileName);
    }

}
