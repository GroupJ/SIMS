/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import java.io.*;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author 20378332
 */
public class CSVFormatFilter extends FileFilter {

     // Supported Format
    private final String[] supportedFormat = {".csv"};

    /**
     * Description of the format filter
     * @return description
     */
    public String getDescription() {
        return ".csv format";
    }

    /**
     * Test if a file is of a supported format.
     * @param f file to test
     * @return true if the file is supported, false otherwise
     */
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String ext = null;
        String name = f.getName();
        int lastindex = name.lastIndexOf('.');

        if (lastindex == -1) {
            return false;
        }

        ext = name.substring(lastindex);

        for (int i = 0; i < supportedFormat.length; i++) {
            if (ext.compareTo(supportedFormat[i]) == 0) {
                return true;
            }
        }

        return false;
    }
}
