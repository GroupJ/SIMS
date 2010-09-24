/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_summary;

import graph.jcharts.*;
import DataStructure.*;
import javax.swing.*;
import javax.swing.JOptionPane.*;
import java.io.*;
import IO.*;
import plugin_engine.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 20378332
 */
public class DSFunctions {

    /**
     * Generates a graph using JCharts.
     * This method has been replaced by JFreeCharts found in
     * GenericSSWindow.java
     * @param tableIndex The use columns
     * @param Rvalue the rvalue
     */
    protected static void generateGraph(int[] tableIndex, int Rvalue) {

        System.err.println(tableIndex.length + " " + Rvalue);

        String[] xAxisLabel = new String[tableIndex.length];
        for (int i = 0; i < tableIndex.length; i++) {
            xAxisLabel[i] = "" + tableIndex[i];
        }

        Rx_DataSet[] ds = new Rx_DataSet[tableIndex.length];

        for (int i = 0; i < tableIndex.length; i++) {

            try {
                double meanValue = Double.parseDouble((String) DSFrontEnd.tableModel.getValueAt(tableIndex[i], 8 + Rvalue * 2));
                double stdErrMean = Double.parseDouble((String) DSFrontEnd.tableModel.getValueAt(tableIndex[i], 8 + Rvalue * 2 + 1));
                stdErrMean = (stdErrMean / 100) * meanValue;
                ds[i] = new Rx_DataSet(meanValue, stdErrMean);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cannot load file # " + tableIndex[i] + " because R" + Rvalue + " not defined.",
                        "No R" + Rvalue + " defined", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        GraphFrontEnd.createGraph(xAxisLabel, ds, "File #", "R-" + Rvalue);
    }

    /**
     * Exports the contents of the summary talbe into a csv file.
     */
    protected static void exportSummaryTable() {

        File dest = IO.FileChooserRequest.getSelectedFile(new String[]{".csv"}, true);

        if (dest != null) {
            if (!dest.getName().contains(".csv")) {
                dest = new File(dest.getAbsolutePath() + ".csv");
            }

            boolean writeToFile = true;

            if (dest.exists()) {
                int response = JOptionPane.showConfirmDialog(null, "File already exist.\nWould you like to overwrite?",
                        "Requesting Permission to Overwrite",
                        JOptionPane.YES_NO_OPTION);
                writeToFile = response == JOptionPane.YES_OPTION;
            }

            if (writeToFile) {

                String[][] grid = new String[DSFrontEnd.tableModel.getRowCount() + 1][DSFrontEnd.tableModel.getColumnCount() - 3];

                for (int i = 0; i < grid[0].length; i++) {
                    grid[0][i] = DSFrontEnd.tableModel.getColumnTitle(i + 3);
                    //System.err.println(grid[0][i] + ":" + grid[0][i].compareTo("(X,Y)"));
                    if (grid[0][i].compareTo("(X,Y)") == 0) {
                        grid[0][i] = grid[0][i].replaceAll("[()]", "");
                    }
                }

                for (int i = 0; i < grid.length - 1; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i + 1][j] = (String) DSFrontEnd.tableModel.getValueAt(i, j + 3);

                        if (grid[0][j].compareTo("X,Y") == 0) {
                            grid[i + 1][j] = grid[i + 1][j].replaceAll("[()]", "");
                        }

                        if (grid[i + 1][j] == null) {
                            grid[i + 1][j] = "";
                        }
                    }
                }
                ExportTable.exportToCSV(grid, dest);
            }

        }
    }

    /**
     * Triggers the plugin system.
     * @param fileName this is not used
     * @param useRow the row indexes of the use data in the summary table
     * @param stdRow the row indexes of the std data in the summary table
     */
    protected static void calcUsingFormula(String fileName, int[] useRow, int[] stdRow) {

        createDatabase(useRow,stdRow);

        File file = IO.FileChooserRequest.getSelectedFile(new String[]{".class", ".java"}, false);

        if (!(file == null)) {

            // redirect std err
            PrintStream ps = System.err;
            try {
                System.setErr(new PrintStream(new FileOutputStream(new File("debug_output.blah"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (SimsPluginLoader.moveFile(file)) {

                System.err.println("Compile Sucessfull\n\n");

                GenericSSWindow newWindow = null;
                DefaultTableModel dtm = null;

                dtm = plugin_engine.SimsPluginLoader.runPlugin("plugin" + File.separator +
                            file.getName().substring(0,file.getName().lastIndexOf('.')) + ".class");

                if (dtm != null)    {
                    System.err.println("Generating display.\n\n");
                    newWindow = new GenericSSWindow(dtm, "Trial", false);
                    newWindow.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                    newWindow.setVisible(true);
                } else  {
                    System.err.println("Cannot generate display.\n\n");
                }
                
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Could not load plugin.\nCheck if the file can be compiled.",
                        "Cannot Load",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                System.err.println("Could not load plugin.\nCheck if the file can be compiled.\n\n");
            }

            // set std err back to normal
            try {
                System.setErr(ps);
            } catch (Exception e) {
                e.printStackTrace();
            }

            showDebugConsole();
        }
    }

    /*
     * Shows a debug terminal for the plugin system
     */
    private static void showDebugConsole()  {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("debug_output.blah"))));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null)
                sb.append(line + "\n");

            new debug.DebugConsole(sb.toString());
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }

    /*
     * Populates the plugin system with the user selected data
     */
    private static void createDatabase(int[] useRow, int[] stdRow)    {
                // get used title
        int numCol = DSFrontEnd.tableModel.getColumnCount();
        String[] title = new String[numCol];
        for (int i = 0; i < title.length; i++) {
            title[i] = DSFrontEnd.tableModel.getColumnTitle(i);
        }

        // get used content
        int numRow = useRow.length;
        String[][] content = new String[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                Object o = DSFrontEnd.tableModel.getValueAt(useRow[i], j);
                if (o != null) {
                    content[i][j] = DSFrontEnd.tableModel.getValueAt(useRow[i], j).toString();
                } else {
                    content[i][j] = null;
                }
            }
        }

        // get std content
        numRow = stdRow.length;
        String[][] standard = new String[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {

                Object o = DSFrontEnd.tableModel.getValueAt(stdRow[i], j);
                if (o != null) {
                    standard[i][j] = o.toString();
                } else {
                    standard[i][j] = null;
                }
            }
        }

        // get user input content
        numRow = DSFrontEnd.inputModel.getRowCount();
        numCol = DSFrontEnd.inputModel.getColumnCount();
        String[][] userInput = new String[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                Object o = DSFrontEnd.inputModel.getValueAt(i, j);
                if (o != null) {
                    userInput[i][j] = o.toString();
                } else {
                    userInput[i][j] = null;
                }
            }
        }

         // get user input title
        numCol = DSFrontEnd.inputModel.getColumnCount();
        String[] userTitle = new String[numCol];
        for (int i = 0; i < userTitle.length; i++) {
            userTitle[i] = DSFrontEnd.inputModel.getColumnTitle(i);
        }

        plugin.Content.create(title, content);
        plugin.Standard.create(title, standard);
        plugin.Input.create(userTitle, userInput);
    }
}
