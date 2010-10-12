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
 * DesktopApplication1.java
 */

package sims2;

import file_list.FileListWindow;
import file_list.FileListFunctions;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.io.*;

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
