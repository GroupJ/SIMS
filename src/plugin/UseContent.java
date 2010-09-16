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
        return Content.getContentByName(name, column);
    }

    public String getContentByFileNumber(String name, String column)    {
        return Content.getContentByFileNumber(name, column);
    }
}
