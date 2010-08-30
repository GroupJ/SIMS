/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file_output;

/**
 * Front End Interface for File Output Sub System
 * @author 20378332
 */
public class FileOutputFrontEnd {

    // file output window
    private static FileOutputDataWindow window = new FileOutputDataWindow();

    /**
     * Displays file output window
     */
    public static void showWindow()    {
        window.setVisible(true);
    }

    /**
     * Hides file output window
     */
    public static void hideWindow()    {
        window.setVisible(false);
    }

    /**
     * Display text in text area.
     * @param text String to be displayed in text area.
     */
    public static void displayData(String text)    {
        window.displayData(text);
    }
}
