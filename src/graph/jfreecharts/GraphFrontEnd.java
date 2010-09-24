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
