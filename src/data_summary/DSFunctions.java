/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import graph.*;
import DataStructure.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane.*;
import java.io.*;
import IO.*;
/**
 *
 * @author 20378332
 */
public class DSFunctions {

    protected static void generateGraph(int[] tableIndex, int Rvalue)    {

        System.err.println(tableIndex.length + " " + Rvalue);

        String[] xAxisLabel = new String[tableIndex.length];
        for (int i = 0; i < tableIndex.length; i++)
            xAxisLabel[i] = "" + tableIndex[i];

        Rx_DataSet[] ds = new Rx_DataSet[tableIndex.length];

        for (int i = 0; i < tableIndex.length; i++) {

            try {
                double meanValue = Double.parseDouble((String) DSFrontEnd.tableModel.getValueAt(tableIndex[i], 7 + Rvalue * 2));
                double stdErrMean = Double.parseDouble((String) DSFrontEnd.tableModel.getValueAt(tableIndex[i], 7 + Rvalue * 2 + 1));
                stdErrMean = (stdErrMean / 100) * meanValue;
                ds[i] = new Rx_DataSet(meanValue, stdErrMean);
            } catch (Exception e)   {
                JOptionPane.showMessageDialog(null, "Cannot load file # " + tableIndex[i] + " because R" + Rvalue + " not defined.",
                        "No R" + Rvalue + " defined", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }

        GraphFrontEnd.createGraph(xAxisLabel, ds, "File #", "R-" + Rvalue);
    }


    protected static void exportSummaryTable()    {

        JFileChooser fc = new JFileChooser();
        FileFilter ff = new CSVFormatFilter();
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(false);

        int result = fc.showSaveDialog(null);

        if (result == fc.APPROVE_OPTION)    {
            File dest = fc.getSelectedFile();

            if (!dest.getName().contains(".csv"))
                dest = new File(dest.getAbsolutePath() + ".csv");
            
            boolean writeToFile = true;

            if (dest.exists())  {
                int response = JOptionPane.showConfirmDialog(null, "File already exist.\nWould you like to overwrite?",
                        "Requesting Permission to Overwrite",
                        JOptionPane.YES_NO_OPTION);
                writeToFile = response == JOptionPane.YES_OPTION;
            }

            if (writeToFile) {

                String[][] grid = new String[DSFrontEnd.tableModel.getRowCount()+1][DSFrontEnd.tableModel.getColumnCount()-3];

                for (int i = 0; i < grid[0].length; i++)    {
                    grid[0][i] = DSFrontEnd.tableModel.getColumnTitle(i + 3);
                    //System.err.println(grid[0][i] + ":" + grid[0][i].compareTo("(X,Y)"));
                    if (grid[0][i].compareTo("(X,Y)") == 0) {
                        grid[0][i] = grid[0][i].replaceAll("[()]", "");
                    }
                }

                for (int i = 0; i < grid.length - 1; i++)
                    for (int j = 0; j < grid[i].length; j++)    {
                        grid[i+1][j] = (String) DSFrontEnd.tableModel.getValueAt(i, j+3);

                        if (grid[0][j].compareTo("X,Y") == 0) {
                            grid[i+1][j] = grid[i+1][j].replaceAll("[()]", "");
                        }

                        if (grid[i+1][j] == null)
                            grid[i+1][j] = "";
                    }
                ExportTable.exportToCSV(grid, dest);
            }
        }
    }

    protected static void calcUsingFormula(String fileName, int[] useRow, int[] stdRow)    {

        int numCol = DSFrontEnd.tableModel.getColumnCount();
        String[] title = new String[numCol];
        for (int i = 0; i < title.length; i++)
            title[i] = DSFrontEnd.tableModel.getColumnTitle(i);

        int numRow = useRow.length;
        String[][] content = new String[numRow][numCol];
        for (int i = 0; i < numRow; i++)
            for (int j = 0; j < numCol; j++)    {
                Object o = DSFrontEnd.tableModel.getValueAt(useRow[i], j);
                if (o != null)
                    content[i][j] = DSFrontEnd.tableModel.getValueAt(useRow[i], j).toString();
                else
                    content[i][j] = "0";
            }

        numRow = stdRow.length;
        String[][] standard = new String[numRow][numCol];
        for (int i = 0; i < numRow; i++)
            for (int j = 0; j < numCol; j++)
                content[i][j] = DSFrontEnd.tableModel.getValueAt(stdRow[i], j).toString();

        plugin.Content.create(title, content);
        plugin.Standard.create(title, standard);

        DSWindow newWindow = new DSWindow(plugin_engine.SimsPluginLoader.runPlugin("plugin/Implementation.class"), "Trial");
        newWindow.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        newWindow.setVisible(true);

    }
}
