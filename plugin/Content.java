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
public class Content {

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

        colTitle.clear();
        rowId.clear();

        for (int i = 0; i < titles.length; i++)
            colTitle.put(titles[i], i);

        int fileId = colTitle.get("File #");
        int fileName = colTitle.get("File Name");

        for (int i = 0; i < content.length; i++)    {
            rowId.put(content[i][fileId], i);
            rowId.put(content[i][fileName], i);
        }
    }

    public static String getContentByName(String name, String column)    {
        int col = colTitle.get(column);
        int row = rowId.get(name);
        return content[row][col];
    }

    public static String getContentByFileNumber(String name, String column)    {
        int col = colTitle.get(column);
        int row = rowId.get(name);
        return content[row][col];
    }

    public static String[][] getAll()   {
        return content;
    }

    public static String[] getTitle()   {
        return titles;
    }

}
