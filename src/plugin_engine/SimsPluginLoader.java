/*
*  This file is part of S:SIMS.
*
*  S:SIMS is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  S:SIMS is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with S:SIMS.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin_engine;

import java.io.*;
import javax.swing.table.DefaultTableModel;
import plugin.Formula;
import plugin.Formula_Iterative;

/**
 * A plugin loader
 * @author 20378332
 */
public class SimsPluginLoader {

    /**
     * Attempts to run the plugin.
     * @param fileName a compiled java file
     * @return a tablemodel containing the contents of the plugin output
     */
    public static DefaultTableModel runPlugin(String fileName)  {

        try {

            File file = new File(fileName);
            if (file.exists())  {

                System.err.println("Plugin located at : " + fileName);
                fileName = fileName.replace(File.separator, ".");
                fileName = fileName.substring(0, fileName.length() - ".class".length());
                Class feature = Class.forName(fileName);

                if (implementsInterface(feature, Formula.class)) {
                    System.err.println("Starting Formula\n\n");
                    return Processor.computeFormula((Formula) feature.newInstance());
                }

                if (implementsInterface(feature, Formula_Iterative.class))  {
                    System.err.println("Starting Iterative\n\n");
                    return Processor.computeForumla_Iterative((Formula_Iterative) feature.newInstance());
                }
                
            } else  {
                System.err.println("Could not load plugin:\n" + file.getAbsolutePath() + "\n\n");
                javax.swing.JOptionPane.showMessageDialog(null, "Could not load plugin:\n" + file.getAbsolutePath(),
                        "Error Loading Plugin",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e)   {
            e.printStackTrace();
        }

        System.err.println("Error : The plugin could not be loaded.\n\n");
        return null;
    }

    /*
     * Checks if a class 'o' implements and interface
     */
    private static boolean implementsInterface(Class o, Class interf)  {
        Class[] interfaces = o.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i].equals(interf))
                return true;
        }
        return false;
    }

    /**
     * Moves the file into the plugin folder
     * @param file the file to move
     * @return true if the file could be moved
     */
    public static boolean moveFile(File file) {

        if (file != null) {

            //move the file
            try {
                File newf = new File("plugin" + File.separator + file.getName());
                File classF = new File("plugin" + File.separator + file.getName().substring(0, file.getName().lastIndexOf('.')) + ".class");

                if (classF.getAbsolutePath().equals(newf.getAbsolutePath()))
                    return true;

                boolean write = true;
                if (classF.exists()) {
                    int response = javax.swing.JOptionPane.showConfirmDialog(null, "Plugin already exist.\nWould you like to overwrite?",
                            "Requesting Permission to Overwrite",
                            javax.swing.JOptionPane.YES_NO_OPTION);
                    write = response == javax.swing.JOptionPane.YES_OPTION;
                }

                if (write) {

                    if (classF.exists())
                        classF.delete();

                    // if not equal move file
                    if (!file.getAbsolutePath().equals(newf.getAbsolutePath())) {
                        FileInputStream fis = new FileInputStream(file);
                        FileOutputStream fos = new FileOutputStream(newf);

                        byte[] buffer = new byte[4096];
                        int bytes;

                        while ((bytes = fis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytes);
                        }

                        fis.close();
                        fos.close();
                    }

                    if (file.getName().contains(".java")) {
                        return compileFile(newf);
                    }

                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /*
     * Attempts to compile the file in the plugin folder
     * @return boolean true if succesful
     */
    private static boolean compileFile(File file) {

        try {

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("javac plugin" + File.separator + file.getName());
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

            int result = pr.waitFor();
            System.err.println("Compiler Ouput:\n\n");
            String line;
            while ((line = input.readLine()) != null) {
                System.err.println(line);
            }

            File classF = new File("plugin" + File.separator + file.getName().substring(0, file.getName().lastIndexOf('.')) + ".class");
            return classF.exists();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
