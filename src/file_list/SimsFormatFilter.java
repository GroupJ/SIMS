/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file_list;

/**
 * Format Filter to use for FileChooser.
 * Current supported format are :
 * .asc
 * @author 20378332
 */

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SimsFormatFilter extends FileFilter {

    // Supported Format
    private final String[] supportedFormat = {".sims"};

    /**
     * Description of the format filter
     * @return description
     */
    public String getDescription() {
        return ".sims format";
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
