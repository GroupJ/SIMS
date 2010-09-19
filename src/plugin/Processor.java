/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 20378332
 */
public class Processor {

    public static DefaultTableModel computeFormula(Formula f)  {

        BlankTableModel btm = new BlankTableModel(f.getColumnTitle());
        f.processStandard(new StandardData());
        f.processUse(new UseContent());
        Object[][] result = f.getResult();

        for (int i = 0; i < result.length; i++)
            btm.addRow(result[i]);

        return btm;
    }

    public static DefaultTableModel computeForumla_Iterative(Formula_Iterative f) {
        
        BlankTableModel btm = new BlankTableModel(f.getColumnTitle());
        f.processStandard(new StandardData());

        String[] title = Content.getTitle();
        int index = -1;
        for (int i = 0; i < title.length; i++)
            if (title[i].compareTo("File #") == 0)
                index = i;

        String[][] content = Content.getAll();
        for (int i = 0; i < content.length; i++)    {
            f.processUse(new UseRowContent(content[i][index]));
            btm.addRow(f.getResult());
        }

        return btm;
    }
}
