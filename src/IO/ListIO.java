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
