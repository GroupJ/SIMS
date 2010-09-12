/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file_list;

import DataStructure.DataHouse;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane.*;
import java.io.*;
import IO.*;
import data_summary.*;
import file_output.*;
import java.util.*;
import java.awt.Point;

/**
 * Implements the functionalities for file_list subsystem.
 * @author 20378332
 */
public class FileListFunctions {

    protected static void saveWindowSettings(FileListWindow flw)  {

        StringBuffer sb = new StringBuffer();
        
        Point topLeft = flw.getLocationOnScreen();
        sb.append("FileListWindow= " + topLeft.x + " " + topLeft.y + " " + flw.getHeight() + " " + flw.getWidth() + "\n");

        FileOutputFrontEnd.showWindow();
        topLeft = FileOutputFrontEnd.getWindowPosition();
        FileOutputFrontEnd.hideWindow();
        sb.append("FileOutputWindow= " + topLeft.x + " " + topLeft.y + " " + FileOutputFrontEnd.getHeight() + " " + FileOutputFrontEnd.getWidth() + "\n");

        DSFrontEnd.showWindow();
        topLeft = DSFrontEnd.getWindowPosition();
        DSFrontEnd.hideWindow();
        sb.append("DataSummaryWindow= " + topLeft.x + " " + topLeft.y + " " + DSFrontEnd.getHeight() + " " + DSFrontEnd.getWidth() + "\n");

        DSFrontEnd.showSecondWindow();
        topLeft = DSFrontEnd.getSecondWindowPosition();
        DSFrontEnd.hideSecondWindow();
        sb.append("InputSummaryWindow= " + topLeft.x + " " + topLeft.y + " " + DSFrontEnd.getSecondHeight() + " " + DSFrontEnd.getSecondWidth() + "\n");

        try {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("SIMS_settings.dat"))));
            ps.print(sb.toString());
            ps.close();
        } catch (Exception e)   {
            e.printStackTrace();
        }
        
    }

    public static void loadSettings(FileListWindow flw)   {

        StringTokenizer st;
        String line;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("SIMS_settings.dat"))));
            
            for (int i = 0; i < 4; i++) {
                line = br.readLine();
                st = new StringTokenizer(line);

                String windowName = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                switch (i)  {
                    case 0: flw.setLocation(x, y);
                            flw.setSize(w, h);
                            break;
                    case 1: FileOutputFrontEnd.setLocation(x,y);
                            FileOutputFrontEnd.setSize(w, h);
                            break;
                    case 2: DSFrontEnd.setLocation(x,y);
                            DSFrontEnd.setSize(w, h);
                            break;
                    case 3: DSFrontEnd.setSecondLocation(x,y);
                            DSFrontEnd.setSecondSize(w, h);
                            break;
                }
            }

            br.close();

        } catch (Exception e)   {
            e.printStackTrace();
            System.err.println("No file found. Skipping settings");
        }
    }

    protected static void saveFileList(ArrayList<DataHouse> list)   {
        JFileChooser fc = new JFileChooser();
        FileFilter ff = new SimsFormatFilter();
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(false);
        
        int result = fc.showSaveDialog(null);

        if (result == fc.APPROVE_OPTION)    {
            File dest = fc.getSelectedFile();

            if (dest.exists())  {
                // do nothing
            } else  {
                ListIO.saveFileList(list, dest.getAbsolutePath() + ".sims");
            }
        }
    }

    protected static void loadFileList(FileListWindow flw)    {
        JFileChooser fc = new JFileChooser();
        FileFilter ff = new SimsFormatFilter();
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(false);

        int result = fc.showOpenDialog(null);

        if (result == fc.APPROVE_OPTION)    {
            File dest = fc.getSelectedFile();
            DataHouse[] dh = ListIO.loadFileList(dest.getAbsolutePath());

            resetTable(flw);

            ArrayList<DataHouse> retVal = new ArrayList<DataHouse> ();
            for (int i = 0; i < dh.length; i++) {
                retVal.add(dh[i]);
                addToSummary(dh[i]);
            }

            flw.files = retVal;
            flw.updateList();
            displayData(dh[0]);

            updateTable();
            showOutputWindow();
            showSummaryWindow();
        }
    }

    protected static boolean requestSystemWipe()  {
        int result = JOptionPane.showConfirmDialog(null,
                "The action you requested will clear the system state.\n" +
                "All loaded files and processing will be removed.\n" +
                "Would you like to continue?", "Request Permission to Wipe",
                JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Removes an input file from the entire system and updates
     * the required GUI components.
     * @param fileName the name of the file
     * @param flw a FileListWindow
     */
    protected static void removeFromSystem(String fileName, FileListWindow flw) {
        DSFrontEnd.removeRow(fileName);
        DSFrontEnd.updateSummaryTable();
        DataHouse dh = new DataHouse();
        dh.fileName = fileName;
        int index = flw.files.indexOf(dh);
        flw.files.remove(index);
        flw.updateList();
    }

    /**
     * Add data house into summary table.
     * @param dh a datahouse
     */
    protected static void addToSummary(DataHouse dh)    {
        String[] arg0,arg1;
        arg0 = getDefaultParam(dh);
        arg1 = getR0ToRn(dh);
        
        DSFrontEnd.addRow(arg0, arg1);
        showSummaryWindow();
    }

    /**
     * Clears the every loaded input file.
     */
    protected static void resetTable(FileListWindow flw)  {
        DSFrontEnd.resetTable();
        flw.files = new ArrayList<DataHouse> ();
        flw.updateList();
        FileOutputFrontEnd.displayData("");
    }

    /**
     * Displays the summary table window.
     */
    protected static void showSummaryWindow()   {
        DSFrontEnd.showWindow();
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
        DSFrontEnd.updateSummaryTable();
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

    /**
     * Prompts user if they want to remove file. This
     * only prompts the user, it does not remove the file.
     * @param dh the file that they want to remove
     * @return true if user selects ok false otherwise
     */
    protected static boolean requestRemove(DataHouse dh)    {
        String mgs = "Would you like to remove " + dh.fileName + " from the list?";
        int result = javax.swing.JOptionPane.showConfirmDialog(null, mgs, "Remove File", javax.swing.JOptionPane.OK_CANCEL_OPTION);
        return result == javax.swing.JOptionPane.YES_OPTION;
    }

    /**
     * Scans and removes already loaded files. The user is asked
     * before a file is overwritten.
     * @param dh a list of new imported files
     * @param alreadyImported an arraylist of already imported files
     * @return DataHouse[] an array of new files to be added.
     */
    protected static DataHouse[] removeRedundancies(DataHouse[] dh, ArrayList<DataHouse> alreadyImported) {

        int index = -1;
        ArrayList<Integer> use = new ArrayList<Integer> ();

        for (int i = 0; i < dh.length; i++) {

            index = alreadyImported.indexOf(dh[i]);

            if (index != -1)    {
                if (requestActionForRedundantFiles(dh[i].fileName)) {
                    int fileIndex = alreadyImported.indexOf(dh[i]);
                    // overwrite previous file;
                    alreadyImported.set(fileIndex, dh[i]);
                }
            } else  {
                use.add(i);
            }
        }

        DataHouse[] result = new DataHouse[use.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = dh[use.get(i)];
        
        return result;
    }

    /**
     * Prompts user for permission to overwrite previously imported files.
     */
    private static boolean requestActionForRedundantFiles(String fileName)   {
        String mgs = "File " + fileName + " is already imported. Would you like to update its contents?";
        int result = javax.swing.JOptionPane.showConfirmDialog(null, mgs, "File Already Found", javax.swing.JOptionPane.OK_CANCEL_OPTION);
        return result == javax.swing.JOptionPane.YES_OPTION;
    }

    protected static void exportSummaryTable()  {
        DSFrontEnd.exportSummaryTable();
    }
}
