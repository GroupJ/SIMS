/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import java.awt.Point;

/**
 * A front end for Data Summary Sub System
 * @author 20378332
 */
public class DSFrontEnd {

    // variables are initialised in order so tablemodel has to come first
    protected static SummaryTableModel tableModel = new SummaryTableModel();
    private static DSWindow window = new DSWindow();

    protected static InputTableModel inputModel = new InputTableModel();
    private static CalcInputWindow inputWindow = new CalcInputWindow();

    public static Point getWindowPosition()  {
        return window.getLocationOnScreen();
    }

    public static int getHeight()   {
        return window.getHeight();
    }

    public static int getWidth()    {
        return window.getWidth();
    }

    public static void setLocation(int x, int y) {
        window.setLocation(x, y);
    }

    public static void setSize(int w,int h)   {
        window.setSize(w,h);
    }

    public static Point getSecondWindowPosition()  {
        return inputWindow.getLocationOnScreen();
    }

    public static int getSecondHeight()   {
        return inputWindow.getHeight();
    }

    public static int getSecondWidth()    {
        return inputWindow.getWidth();
    }

    public static void setSecondLocation(int x, int y) {
        inputWindow.setLocation(x, y);
    }

    public static void setSecondSize(int w,int h)   {
        inputWindow.setSize(w,h);
    }

    /**
     * Resets everything to default.
     */
    public static void init()   {
        resetTable();
        window.setVisible(true);
    }

    /**
     * Displays summary window.
     */
    public static void showWindow()  {
        window.setVisible(true);
    }

    /**
     * Displays input window
     */
    public static void showSecondWindow()   {
        inputWindow.setVisible(true);
    }

    /**
     * Hide summary window.
     */
    public static void hideWindow() {
        window.setVisible(false);
    }

        /**
     * Hide summary window.
     */
    public static void hideSecondWindow() {
        inputWindow.setVisible(false);
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
        updateSummaryTable();
        inputModel.addRow(arg0[0], arg1.length / 2);
        updateInputTable();
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

    public static void updateInputTable()   {
        inputModel.fireTableDataChanged();
        inputModel.fireTableStructureChanged();
        inputWindow.resetColWidth();
        showSecondWindow();
    }

    /**
     * Removes a row entry in the table based on the file name.
     * @param fileName the filename
     */
    public static void removeRow(String fileName)   {
        tableModel.removeRow(fileName);
    }

    public static void exportSummaryTable() {
        DSFunctions.exportSummaryTable();
    }
}
