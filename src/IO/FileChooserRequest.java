/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.util.*;

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

        if (save) {
            result = fc.showSaveDialog(null);
        } else {
            result = fc.showOpenDialog(null);
        }

        if (result == JFileChooser.APPROVE_OPTION) {
            lastFolder = fc.getCurrentDirectory();
            lastFolder = new File(lastFolder.getAbsolutePath() + "/.");

            if (fc.getSelectedFile().getName().length() > 0) {
                if (fc.getSelectedFile().getName().charAt(0) != '.') {

                    if (save && !fc.getSelectedFile().canWrite() && fc.getSelectedFile().exists()) {
                        javax.swing.JOptionPane.showMessageDialog(null,
                                "Cannot write to file " +
                                fc.getSelectedFile() + ".\nInsufficient permission.",
                                "OS restriction",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                    if (!save && !fc.getSelectedFile().canRead()) {
                        javax.swing.JOptionPane.showMessageDialog(null,
                                "Cannot read from file " +
                                fc.getSelectedFile() + ".\nInsufficient permission.",
                                "OS restriction",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                    if (save) {
                        return (fc.getSelectedFile().canWrite() || !fc.getSelectedFile().exists()) ? fc.getSelectedFile() : null;
                    }

                    if (!save) {
                        return fc.getSelectedFile().canRead() ? fc.getSelectedFile() : null;
                    }
                }
            }

            javax.swing.JOptionPane.showMessageDialog(null,
                    "Invalid file name.", "Invalid Character",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
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

        if (save) {
            result = fc.showSaveDialog(null);
        } else {
            result = fc.showOpenDialog(null);
        }

        if (result == JFileChooser.APPROVE_OPTION) {
            lastFolder = fc.getCurrentDirectory();
            lastFolder = new File(lastFolder.getAbsolutePath() + "/.");

            File[] files = fc.getSelectedFiles();

            ArrayList<String> errors = new ArrayList<String>();

            for (int i = 0; i < files.length; i++) {

                if ((save && !files[i].canWrite() && files[i].exists()) || (!save && !files[i].canRead()))
                    errors.add(files[i].getName());
                
                if (save) {
                    files[i] = (files[i].canWrite() || !files[i].exists()) ? files[i] : null;
                } else {
                    files[i] = files[i].canRead() ? files[i] : null;
                }
            }

            if (errors.size() >= 1) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < errors.size(); i++)
                    sb.append(errors.get(i) + "\n");

                javax.swing.JOptionPane.showMessageDialog(null,
                        "Cannot " + (save ? "write to" : "read from") + " the following files:\n" +
                        sb.toString(),
                        "OS restriction",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }

            return files;
        }

        return null;
    }
}
