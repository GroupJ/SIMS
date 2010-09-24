/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

import java.util.*;

/**
 *
 * @author 20378332
 */
public class Input {

    protected static HashMap<String,Integer> colTitle = new HashMap<String,Integer> ();
    protected static HashMap<String,Integer> rowId = new HashMap<String,Integer> ();
    private static String[] titles;
    private static String[][] content;


    public static void create(String[] colTitles, String[][] tableContent)  {
        titles = colTitles;
        content = tableContent;
        generateIndexes();
    }

    private static void generateIndexes()   {

        System.err.println(content[0][1]);

        colTitle.clear();
        rowId.clear();

        for (int i = 0; i < titles.length; i++)
            colTitle.put(titles[i], i);

        int fileName = colTitle.get("Std Name");

        for (int i = 0; i < content.length; i++)    {
            rowId.put(content[i][fileName], i);
        }
    }

    public static String getContentByName(String name, String column) throws Exception  {
        int col = colTitle.get(column);
        int row = rowId.get(name);

        if (!isValid(row,col))
            throw new Exception("Out of Bounds");

        if (content[row][col] == null)
            throw new Exception("No content");

        System.err.println(content[row][col]);

        return content[row][col];
    }

    public static String getContentByFileNumber(String name, String column) throws Exception    {
        int col = colTitle.get(column);
        int row = rowId.get(name);

        if (!isValid(row,col))
            throw new Exception("Out of Bounds");

        if (content[row][col] == null)
            throw new Exception("No content");

        System.err.println(content[row][col]);

        return content[row][col];
    }

    private static boolean isValid(int row, int col)    {
        return row >= 0 && row < content.length && col >= 0 && col < titles.length;
    }
}
