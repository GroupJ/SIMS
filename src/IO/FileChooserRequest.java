/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IO;

import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author ragil
 */
public class FileChooserRequest {

    private static File lastFolder = null;

    public static File getSelectedFile(String[] fileFormat, boolean save) {

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(lastFolder);
        FileFilter ff = new GenericFileFilter(fileFormat);
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(false);

        int result;

        if (save)
            result = fc.showSaveDialog(null);
        else
            result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            lastFolder = fc.getCurrentDirectory();
            lastFolder = new File(lastFolder.getAbsolutePath() + "/.");
            return fc.getSelectedFile();
        }

        return null;
    }

    public static File[] getSelectedFiles(String[] fileFormat, boolean save) {

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(lastFolder);
        FileFilter ff = new GenericFileFilter(fileFormat);
        fc.setFileFilter(ff);
        fc.setMultiSelectionEnabled(true);

        int result;

        if (save)
            result = fc.showSaveDialog(null);
        else
            result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            lastFolder = fc.getCurrentDirectory();
            lastFolder = new File(lastFolder.getAbsolutePath() + "/.");
            return fc.getSelectedFiles();
        }

        return null;
    }
}
