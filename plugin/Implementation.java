/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author ragil
 */
public class Implementation implements Formula_Iterative    {

    Double[] result = new Double[1];

    public void processStandard(StandardData std)   {

    }

    public void processUse(UseRowContent use)   {

        result[0] = Double.parseDouble(use.getContentByTitle("R0 (MV)")) + 2;
    }

    public String[] getColumnTitle()    {
        String[] column_title = new String[1];

        column_title[0] = "R0-mean + 2";
        
        return column_title;
    }

    public Object[] getResult() {
        return result;
    }
}
