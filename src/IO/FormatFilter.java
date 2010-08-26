/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

/**
 *
 * @author 20378332
 */

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FormatFilter extends FileFilter {

    public final String[] supportedFormat = {".asc"};

    public String getDescription() {
        return ".asc format";
    }

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
