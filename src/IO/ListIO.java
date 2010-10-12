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

import DataStructure.DataHouse;
import java.util.*;
import java.io.*;

/**
 * ListIO is used to save and load the user's file list.
 * @author 20378332
 */
public class ListIO {

    /**
     * Save the absolute path of each datahouse in the list.
     * Each datahouse location is saved in order as passed in the list.
     * #WARNING - the output file will be overwritten if it already exist.
     * @param list An arraylist of datahouses
     * @param outputDest the output file.
     */
    public static void saveFileList(ArrayList<DataHouse> list, String outputDest)   {
        try {
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(outputDest))));
            int size = list.size();
            for (int i = 0; i < size; i++)
                ps.printf("%s\n", list.get(i).absolutePath);
            ps.close();
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }

    /**
     * Load a file containing the absolute path of one or more input files
     * with each file path seperated on a single line.
     * @param source The config file location
     * @return Datahouse[] an array of datahouses. A null entry refers to a
     *          file that cannot be loaded.
     */
    public static DataHouse[] loadFileList(String source)   {
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(source))));
            ArrayList<String> files = new ArrayList<String> ();
            String temp;
            while ((temp = br.readLine()) != null)  {
                files.add(temp);
            }

            DataHouse[] dh = new DataHouse[files.size()];
            for (int i = 0; i < dh.length; i++) {
                dh[i] = ReadInput.readInput(new File(files.get(i)));
            }

            return dh;
        } catch (Exception e)   {
            e.printStackTrace();
        }

        return null;
    }
}
