/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin_engine;

import java.io.*;
import javax.swing.table.DefaultTableModel;
import plugin.Formula;
import plugin.Formula_Iterative;
import plugin.Processor;

/**
 *
 * @author 20378332
 */
public class SimsPluginLoader {

    public static DefaultTableModel runPlugin(String fileName)  {

        try {

            File file = new File(fileName);
            if (file.exists())  {

                System.err.println(fileName);
                fileName = fileName.replace('/', '.');
                fileName = fileName.substring(0, fileName.length() - ".class".length());
                Class feature = Class.forName(fileName);

                if (implementsInterface(feature, Formula.class)) {
                    System.err.println("Starting Formula");
                    return Processor.computeFormula((Formula) feature.newInstance());
                }

                if (implementsInterface(feature, Formula_Iterative.class))  {
                    System.err.println("Starting Iterative");
                    return Processor.computeForumla_Iterative((Formula_Iterative) feature.newInstance());
                }
                
            } else  {
                javax.swing.JOptionPane.showMessageDialog(null, "Could not load plugin:\n" + file.getAbsolutePath());
            }

        } catch (Exception e)   {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean implementsInterface(Class o, Class interf)  {
        Class[] interfaces = o.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.err.println(interfaces[i]);
            if (interfaces[i].equals(interf))
                return true;
        }
        return false;
    }
}
