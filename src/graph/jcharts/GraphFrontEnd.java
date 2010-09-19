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

    public static void createGraph(String[] xAxisLabels, Rx_DataSet[] values, String xAxisTitle, String yAxisTitle) {
        AxisChart ac = GraphFunctions.createChart(xAxisLabels, values, xAxisTitle, yAxisTitle, 1, 0);
        new GraphWindow(ac).setVisible(true);
    }
    
}
