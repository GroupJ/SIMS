/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author ragil
 */
public class UserInputData {

    public String getContentByName(String name, String column)  {
        try {
            return Input.getContentByName(name, column);
        } catch (Exception e)   {
            String mgs = "Cannot access element by name : \"" +
                    name + "\" and column : \"" + column + "\"";
            System.err.println(mgs);
            javax.swing.JOptionPane.showMessageDialog(null, mgs,
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
}
