/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package file_output;

import java.awt.Point;

/**
 * Front End Interface for File Output Sub System
 * @author 20378332
 */
public class FileOutputFrontEnd {

    // file output window
    private static FileOutputDataWindow window = new FileOutputDataWindow();

    /**
     * Returns the output window position
     * @return Point
     */
    public static Point getWindowPosition()  {
        return window.getLocationOnScreen();
    }

    /**
     * Returns the output window height
     * @return int height
     */
    public static int getHeight()   {
        return window.getHeight();
    }

    /**
     * Returns the output window width
     * @return int width
     */
    public static int getWidth()    {
        return window.getWidth();
    }

    /**
     * Sets the position of the output window
     * @param x xpos
     * @param y ypos
     */
    public static void setLocation(int x, int y) {
        window.setLocation(x, y);
    }

    /**
     * Sets the dimensions of the output window
     * @param w width
     * @param h height
     */
    public static void setSize(int w,int h)   {
        window.setSize(w,h);
    }

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
