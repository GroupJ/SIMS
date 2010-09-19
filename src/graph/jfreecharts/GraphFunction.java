/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.jfreecharts;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.*;
import org.jfree.chart.urls.*;
import java.text.*;

/**
 *
 * @author ragil
 */
public class GraphFunction {

    private static ChartTheme currentTheme = new StandardChartTheme("JFree");

    protected static void plotData(String title, String xlabel, String ylabel,
            String[] legend, double[][][] data)  {

        DefaultIntervalXYDataset xydata = new DefaultIntervalXYDataset();

        for (int i = 0; i < legend.length; i++) {
            xydata.addSeries(legend[i], data[i]);
        }
        // create a chart...
        JFreeChart chart = GraphFunction.createXYIntervalChart(title, xlabel, ylabel, xydata, PlotOrientation.VERTICAL, true, true, false);
        // create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    private static JFreeChart createXYIntervalChart(String title,
                                               String xAxisLabel,
                                               String yAxisLabel,
                                               XYDataset dataset,
                                               PlotOrientation orientation,
                                               boolean legend,
                                               boolean tooltips,
                                               boolean urls) {

        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYErrorRenderer renderer = new XYErrorRenderer();

        renderer.setDrawXError(true);
        renderer.setDrawYError(true);
        
        xAxis.setNumberFormatOverride(new DecimalFormat("0.######E0"));
        yAxis.setNumberFormatOverride(new DecimalFormat("0.######E0"));
        
        xAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRangeIncludesZero(false);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        currentTheme.apply(chart);
        return chart;

    }
}
