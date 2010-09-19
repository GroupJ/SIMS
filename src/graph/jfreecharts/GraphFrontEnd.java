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

    public static void plotData(String title, String xlabel, String ylabel, String[] legend, double[][][] data)  {
        GraphFunction.plotData(title, xlabel, ylabel, legend, data);
    }
}
