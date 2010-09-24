/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author 20378332
 */
public class UseContent {

    public String getContentByName(String name, String column)  {
        try {
            return Content.getContentByName(name, column);
        } catch (Exception e)   {
            System.err.println("Cannot access element by name : \"" +
                    name + "\" and column : \"" + column + "\"");
            e.printStackTrace();
        }
        return null;
    }

    public String getContentByFileNumber(String name, String column)    {
        try {
            return Content.getContentByFileNumber(name, column);
        } catch (Exception e)   {
            System.err.println("Cannot access element by name : \"" +
                    name + "\" and column : \"" + column + "\"");
            e.printStackTrace();
        }
        return null;
    }
}
