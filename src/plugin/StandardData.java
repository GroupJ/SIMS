/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author ragil
 */
public class StandardData {

    public String getContentByName(String name, String column)  {
        try {
            return Standard.getContentByName(name, column);
        } catch (Exception e)   {
            System.err.println("Cannot access element by name : \"" +
                    name + "\" and column : \"" + column + "\"");
            e.printStackTrace();
        }
        return null;
    }

    public String getContentByFileNumber(String name, String column)    {
        try {
            return Standard.getContentByFileNumber(name, column);
        } catch (Exception e)   {

            String mgs = "Cannot access element by name : \"" +
                    name + "\" and column : \"" + column + "\"";
            System.err.println(mgs);
            javax.swing.JOptionPane.showMessageDialog(null, mgs,
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            new Exception();
            e.printStackTrace();
        }
        return null;
    }

    public String[] getAllStd(String column)    {
        try {
            return Standard.getAllStd(column);
        } catch (Exception e)   {
            System.err.println("Cannot access elements by column : \"" + column + "\"");
            e.printStackTrace();
        }
        return null;
    }
}
