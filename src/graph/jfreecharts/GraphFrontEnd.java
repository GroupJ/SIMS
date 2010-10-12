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

package graph.jfreecharts;

/**
 *
 * @author ragil
 */
public class GraphFrontEnd {

    /**
     * Plots an xyerror graph using jfreecharts
     * @param title the title of the graph
     * @param xlabel the x axis title
     * @param ylabel the y axis title
     * @param legend the legend
     * @param data the data values
     */
    public static void plotData(String title, String xlabel, String ylabel, String[] legend, double[][][] data)  {
        GraphFunction.plotData(title, xlabel, ylabel, legend, data);
    }
}
