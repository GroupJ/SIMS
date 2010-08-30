/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file_list;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import DataStructure.*;
import IO.*;
import data_summary.*;
import file_output.*;

/**
 * Implements the functionalities for file_list subsystem.
 * @author 20378332
 */
public class FileListFunctions {

    /**
     * Add data house into summary table.
     * @param dh a datahouse
     */
    protected static void addToSummary(DataHouse dh)    {
        String[] arg0,arg1;
        arg0 = getDefaultParam(dh);
        arg1 = getR0ToRn(dh);
        DataSummaryFrontEnd.addRow(arg0, arg1);
        showSummaryWindow();
    }

    /**
     * Clears the summary table.
     */
    protected static void resetTable()  {
        DataSummaryFrontEnd.resetTable();
    }

    /**
     * Displays the summary table window.
     */
    protected static void showSummaryWindow()   {
        DataSummaryFrontEnd.showWindow();
    }

    /**
     * Hides the summary table window.
     */
    protected static void showOutputWindow()    {
        FileOutputFrontEnd.showWindow();
    }

    /**
     * Returns the filename used to create the DataHouse
     * @param dh a datahouse
     * @return the file name that created the data house.
     */
    protected static String getFileName(DataHouse dh)   {
        return dh.fileName;
    }

    /**
     * Display a datahouse in file output window.
     * @param dh a datahouse
     */
    protected static void displayData(DataHouse dh) {
        FileOutputFrontEnd.displayData(dh.toString());
    }

    /**
     * Update the summary table. This is required after every update.
     */
    protected static void updateTable() {
        DataSummaryFrontEnd.updateSummaryTable();
    }

    /**
     * Returns the common parameters of a datahouse.
     * The following are returned.
     * return[0] = filename
     * return[1] = (x,y)
     * return[2] = primary current start
     * return[3] = primary current end
     * @param dh a datahouse
     * @return String[] refer to description for details.
     */
    private static String[] getDefaultParam(DataHouse dh)    {
        String[] result = new String[4];

        result[0] = dh.fileName;
        result[1] = "(" + dh.x_pos + "," + dh.y_pos + ")";
        result[2] = "" + dh.PR_start;
        result[3] = "" + dh.PR_end;
        
        return result;
    }

    /**
     * Returns the mean and std error (%) for R0 to Rn in the
     * following format:
     * result[0] = R0 mean
     * result[1] = R0 std-error
     * result[2] = R1 mean
     * result[3] = R1 std-error
     * result[n] = R(n/2) mean
     * result[n+1] = R(floor(n+1)/2) std-error
     * @param dh a datahouse
     * @return String[] refer to description for details.
     */
    private static String[] getR0ToRn(DataHouse dh) {
        String[] result = new String[dh.IR.size() * 2];

        for (int i = 0; i < result.length; i += 2)  {
            result[i] = dh.CR_Grid.get(i/2).get(0);
            result[i+1] = dh.CR_Grid.get(i/2).get(2);
        }

        return result;
    }

    /**
     * Creates a FileChooser and loads selected input files.
     * Returns null reference if a major error occurs.
     * Array entries can be null if file could not be opened.
     * @return DataHouse[]
     */
    protected static DataHouse[] loadInput()   {
        FileFilter ff = new FormatFilter();
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(true);

        DataHouse[] dh = null;

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File[] file = fc.getSelectedFiles();

            if (file == null)
                return null;

            dh = new DataHouse[file.length];
            for (int i = 0; i < file.length; i++)   {
                try {
                    dh[i] = ReadInput.readInput(file[i]);
                } catch (Exception e)   {
                    e.printStackTrace();
                    dh[i] = null;
                }
            }
        }

        return dh;
    }
}
