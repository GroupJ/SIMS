/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

import java.io.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 20378332
 */
public class SimsPluginLoader {

    public static DefaultTableModel runPlugin(String fileName)  {

        FileClassLoader fcl = new FileClassLoader();

        try {

            File file = new File(fileName);
            if (file.exists())  {

                Class feature = fcl.createClass(new File(fileName));

                if (implementsInterface(feature, Formula.class)) {
                    return Processor.computeFormula((Formula) feature.newInstance());
                }

                if (implementsInterface(feature, Formula_Iterative.class))  {
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

    private static boolean implementsInterface(Object o, Class interf)  {
        for (Class c : o.getClass().getInterfaces())
            if (c.equals(interf))
                return true;
        return false;
    }
}
