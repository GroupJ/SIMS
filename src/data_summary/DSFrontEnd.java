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
    private static GenericSSWindow window = new GenericSSWindow(tableModel, "Summary Window", true, new int[] {40,30,30,100,100,100,120,120});

    protected static InputTableModel inputModel = new InputTableModel();
    private static CalcInputWindow inputWindow = new CalcInputWindow();

    /**
     * Returns the window summary window position.
     * @return Point
     */
    public static Point getSummaryWindowPosition()  {
        return window.getLocationOnScreen();
    }

    /**
     * Returns the height of the summary window.
     * @return int height
     */
    public static int getSummaryHeight()   {
        return window.getHeight();
    }

    /**
     * Returns the width of the summary window.
     * @return int width
     */
    public static int getSummaryWidth()    {
        return window.getWidth();
    }

    /**
     * Moves the summary window to an x y point.
     * (0,0) is located at the top left of the screen.
     * @param x xpos
     * @param y ypos
     */
    public static void setSummaryLocation(int x, int y) {
        window.setLocation(x, y);
    }

    /**
     * Sets the summary window dimensions.
     * @param w width
     * @param h height
     */
    public static void setSummarySize(int w,int h)   {
        window.setSize(w,h);
    }

    /**
     * Returns the position of the input window.
     * @return Point
     */
    public static Point getInputWindowPosition()  {
        inputWindow.setVisible(true);
        return inputWindow.getLocationOnScreen();
    }

    /**
     * Returns the height dimension of the input window.
     * @return int height dimension
     */
    public static int getInputWindowHeight()   {
        return inputWindow.getHeight();
    }

    /**
     * Returns the width dimension of the input window.
     * @return int width dimension
     */
    public static int getInputWindowWidth()    {
        return inputWindow.getWidth();
    }

    /**
     * Moves the input window to an x y point.
     * (0,0) is located at the top left of the screen.
     * @param x xpos
     * @param y ypos
     */
    public static void setInputWindowLocation(int x, int y) {
        inputWindow.setLocation(x, y);
    }

    /**
     * Sets the input window dimensions.
     * @param w width
     * @param h height
     */
    public static void setInputWindowSize(int w,int h)   {
        inputWindow.setSize(w,h);
    }

    /**
     * Resets the contents of the summary window to default.
     * This clears any content.
     */
    public static void init()   {
        resetSummaryTable();
        window.setVisible(true);
    }

    /**
     * Displays summary window.
     */
    public static void showSummaryWindow()  {
        window.setVisible(true);
    }

    /**
     * Displays input window
     */
    public static void showInputWindow()   {
        inputWindow.setVisible(false);
    }

    /**
     * Hide summary window.
     */
    public static void hideSummaryWindow() {
        window.setVisible(false);
    }

    /**
     * Hide summary window.
     */
    public static void hideInputWindow() {
        inputWindow.setVisible(false);
    }

    /**
     * Reset the contents of the summary table.
     */
    public static void resetSummaryTable() {
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
        window.resetSize();
        window.setComboBox();

        if (tableModel.getRowCount() != 0)
            window.setEnabled(true);
        else
            window.setEnabled(false);
        
        showSummaryWindow();
    }

    /**
     * Update the input table. This is required to make
     * changes appear.
     */
    public static void updateInputTable()   {
        inputModel.fireTableDataChanged();
        inputModel.fireTableStructureChanged();
        inputWindow.resetColWidth();
        showInputWindow();
    }

    /**
     * Removes a row entry in the table based on the file name.
     * @param fileName the filename
     */
    public static void removeRow(String fileName)   {
        tableModel.removeRow(fileName);
    }

    /**
     * Triggers the program to export the contents of the
     * summary table.
     */
    public static void exportSummaryTable() {
        DSFunctions.exportSummaryTable();
    }
}
