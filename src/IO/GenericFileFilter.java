/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IO;

import javax.swing.filechooser.FileFilter;
import java.io.*;

/**
 *
 * @author ragil
 */
public class GenericFileFilter extends FileFilter   {

    private String[] formats;
    public GenericFileFilter(String[] args) {
        formats = args;
    }

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
