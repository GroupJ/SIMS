/*
 * DesktopApplication1.java
 */

package sims2;

import file_list.FileListWindow;
import file_list.FileListFunctions;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class SIMS2App extends SingleFrameApplication {

    public static FileListWindow fileListWindow = new FileListWindow();

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {

    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication1
     */
    public static SIMS2App getApplication() {
        return Application.getInstance(SIMS2App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        fileListWindow.setVisible(true);
        FileListFunctions.loadSettings(fileListWindow);
    }
}
