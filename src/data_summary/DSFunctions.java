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
            dest = new File(dest.getAbsolutePath() + ".csv");

            if (dest.exists())  {
                JOptionPane.showConfirmDialog(null, "File already exist.\nWould you like to overwrite?",
                        "Permission to Overwrite",
                        JOptionPane.YES_NO_OPTION);
            } else  {

                String[][] grid = new String[DSFrontEnd.tableModel.getRowCount()][DSFrontEnd.tableModel.getColumnCount()-3];
                for (int i = 0; i < grid.length; i++)
                    for (int j = 0; j < grid[i].length; j++)    {
                        grid[i][j] = (String) DSFrontEnd.tableModel.getValueAt(i, j+3);
                        if (grid[i][j] == null)
                            grid[i][j] = "";
                    }
                ExportTable.exportToCSV(grid, dest);
            }
        }
    }
}
