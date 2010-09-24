/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin_engine;

import plugin.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 20378332
 */
public class Processor {

    public static DefaultTableModel computeFormula(Formula f)  {

        System.err.println("Calling getColumnTitle();\n\n");
        BlankTableModel btm = new BlankTableModel(f.getColumnTitle());

        System.err.println("Calling processUserInput();\n\n");
        f.processUserInput(new UserInputData());

        System.err.println("Calling processStandard();\n\n");
        f.processStandard(new StandardData());

        System.err.println("Calling processUse();\n\n");
        f.processUse(new UseContent());

        System.err.println("Calling getResult();\n\n");
        Object[][] result = f.getResult();

        for (int i = 0; i < result.length; i++)
            btm.addRow(result[i]);

        return btm;
    }

    public static DefaultTableModel computeForumla_Iterative(Formula_Iterative f) {

        System.err.println("Calling getColumnTitle();\n\n");
        BlankTableModel btm = new BlankTableModel(f.getColumnTitle());

        System.err.println("Calling processUserInput();\n\n");
        f.processUserInput(new UserInputData());

        System.err.println("Calling processStandard();\n\n");
        f.processStandard(new StandardData());

        String[] title = Content.getTitle();
        int index = -1;
        for (int i = 0; i < title.length; i++)
            if (title[i].compareTo("File #") == 0)
                index = i;

        String[][] content = Content.getAll();
        for (int i = 0; i < content.length; i++)    {

            System.err.println("Calling processUse(); for row " + content[i][index] + "\n\n");
            f.processUse(new UseRowContent(content[i][index]));
            System.err.println("Calling getResult(); for row " + content[i][index] + "\n\n");
            btm.addRow(f.getResult());
        }

        return btm;
    }
}
