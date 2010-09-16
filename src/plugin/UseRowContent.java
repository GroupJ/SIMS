/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author 20378332
 */
public class UseRowContent {

    private String id;

    public UseRowContent(String id) {
        this.id = id;
    }

    public String getContentByTitle(String colName)  {
        return Content.getContentByFileNumber(id, colName);
    }
}
