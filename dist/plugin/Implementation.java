package plugin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import plugin.*;

/**
 *
 * @author ragil
 */
public class Implementation implements Formula_Iterative    {

    private int numCol = 3;
    Object[] result = new Object[numCol];

    public void processStandard(StandardData std)   {

    }

    public void processUse(UseRowContent use)   {
        result[0] = use.getContentByTitle("File #");
        result[1] = new Double(use.getContentByTitle("R0 (MV)"));
        result[2] = new Double(use.getContentByTitle("R0 (Std Err(%))"));
    }

    public String[] getColumnTitle()    {
        String[] column_title = new String[numCol];

        column_title[0] = "File #";
        column_title[1] = "R0-mean";
        column_title[2] = "R0-err";
        
        return column_title;
    }

    public Object[] getResult() {
        return result;
    }
}
