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

package graph.jcharts;

import DataStructure.*;
import org.jCharts.axisChart.*;

/**
 * FrontEnd for graph system. Allows the user to create a new graph
 * that appears in its own window.
 * @author 20378332
 */
public class GraphFrontEnd {

    /**
     * Generates a graph using JCharts
     * @param xAxisLabels the x axis labels
     * @param values the rxdata values
     * @param xAxisTitle the x axis title
     * @param yAxisTitle the y axis title
     */
    public static void createGraph(String[] xAxisLabels, Rx_DataSet[] values, String xAxisTitle, String yAxisTitle) {
        AxisChart ac = GraphFunctions.createChart(xAxisLabels, values, xAxisTitle, yAxisTitle, 1, 0);
        new GraphWindow(ac).setVisible(true);
    }
    
}
