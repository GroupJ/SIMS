/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IO;

import java.io.*;

/**
 *
 * @author 20378332
 */
public class ExportTable {

    public static void exportToCSV(String[][] grid, File file) {

        try {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < grid.length; i++)   {
                 for (int j = 0; j < grid[i].length; j++)   {
                     if (j != 0)
                         sb.append(",");
                     sb.append(grid[i][j]);
                 }
                 sb.append("\r\n");
            }

            ps.print(sb.toString());
            ps.close();
            
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }
}
