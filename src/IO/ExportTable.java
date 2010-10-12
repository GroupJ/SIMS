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

package IO;

import java.io.*;

/**
 *
 * @author 20378332
 */
public class ExportTable {

    /**
     * Exports a 2D matrix of strings into a comma delimited file.
     * #Warning, the file will be overwritten if it already exist.
     * @param grid the 2d matrix
     * @param file the output file
     */
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
