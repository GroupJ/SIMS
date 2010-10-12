/*
*  This file is part of S:SIMS.
*
*  S:SIMS is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  S:SIMS is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with S:SIMS.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin_engine;

import plugin.*;
import javax.swing.table.DefaultTableModel;

/**
 * Code that runs the plugin
 * @author 20378332
 */
public class Processor {

    /**
     * Computes sequentially
     * @param f
     * @return
     */
    protected static DefaultTableModel computeFormula(Formula f)  {

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

    /**
     * Computes iteratively
     * @param f
     * @return
     */
    protected static DefaultTableModel computeForumla_Iterative(Formula_Iterative f) {

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
