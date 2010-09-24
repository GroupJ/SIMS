/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IO;

import javax.swing.filechooser.FileFilter;
import java.io.*;

/**
 * A generic file filter class
 * @author ragil
 */
public class GenericFileFilter extends FileFilter   {

    private String[] formats;

    /**
     * Creates a file filter with args as the supported file formats
     * @param args an array containing all file formats to be shown.
     */
    public GenericFileFilter(String[] args) {
        formats = args;
    }

    /**
     * Overwrite class method
     * @param file
     * @return
     */
    public boolean accept(File file)    {

        if (file.isFile())  {
            String name = file.getName();
            for (int i = 0; i < formats.length; i++)    {
                if (name.contains(formats[i]))
                    return true;
            }

            return false;
        }

        return true;
    }

    /**
     * The description is a string literal of all accepted formats
     * delimited by a comma
     * @return String - description
     */
    public String getDescription()  {
        String description = "";

        for (int i = 0; i < formats.length; i++)    {
            if (i != 0)
                description += ", ";
            description += formats[i];
        }

        return description;
    }
    
}
