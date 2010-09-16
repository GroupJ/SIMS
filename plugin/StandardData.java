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
        return Standard.getContentByName(name, column);
    }

    public String getContentByFileNumber(String name, String column)    {
        return Standard.getContentByFileNumber(name, column);
    }
}
