/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import DataStructure.Rx_DataSet;
import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.axisChart.*;
import org.jCharts.properties.util.ChartFont;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * Provides the functions for graphing
 * @author 20378332
 */
public class GraphFunctions {

    private static int graphWidth = 300;
    private static int graphHeight = 450;

    protected static void setSize(int w, int h) {
        graphWidth = w;
        graphHeight = h;
    }

    protected static BufferedImage setImageSize(BufferedImage bi, int width, int height) {
        BufferedImage result = new BufferedImage(width,height,bi.getType());
        Graphics2D g2 = result.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        double scalex = (double) width / bi.getWidth();
        double scaley = (double) height / bi.getHeight();
        AffineTransform xform = AffineTransform.getScaleInstance(scalex, scaley);
        g2.drawRenderedImage(bi, xform);
        g2.dispose();
        return result;
    }

    protected static void reScale(AxisChart ac, double min, double max, double yScale, double offset) {

        DataAxisProperties dataAxisProperties= (DataAxisProperties) ac.getAxisProperties().getYAxisProperties();

        //setting scale
        double increment = ((max - min) * yScale) / 10;
        double lowerBound = (min + (max - min)/2) - (increment * (5.5 ));
        int numOfIncrements = 11;

        int exponent = -8;

        for (int i = 1; true; i++)  {
            double tempMin = Math.abs(increment);

            if (tempMin >= 1)   {
                tempMin = tempMin / (Math.pow(10, i));
                exponent++;
                if (tempMin <= 1)
                    break;
            } else  {
                tempMin = tempMin * (Math.pow(10, i));
                exponent--;
                if (tempMin >= 1)
                    break;
            }

        }

        System.err.println("lowerbound " + lowerBound + " increment " + increment + " exponent " + exponent);

        try {
            dataAxisProperties.setUserDefinedScale(lowerBound, increment);
            dataAxisProperties.setNumItems(numOfIncrements);
            dataAxisProperties.setRoundToNearest(exponent);
            dataAxisProperties.setShowZeroLine(false);

            //System.err.println("min " + (min - (max - min)/10) + " max " + (max + (max - min)/10) + " increments "+ dataAxisProperties.getUserDefinedIncrement());
        } catch (Exception e)   {
            e.printStackTrace();
        }
    }

    private static void calcWidth(int x_length) {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double entryWidth = dim.getWidth() / x_length;
        graphWidth = 125 + (int)((double)x_length * (entryWidth > 50 ? 50 : entryWidth));
    }

    protected static AxisChart createChart(String[] xAxisLabels, Rx_DataSet[] values, String xAxisTitle, String yAxisTitle, double yScale, double offset)    {
        calcWidth(xAxisLabels.length);

        DataSeries ds = new DataSeries(xAxisLabels, null, null, null);
        StockChartDataSet scd = null;

        double[] high = new double[values.length];
        double[] low = new double[values.length];
        double[] meanVal = new double[values.length];
        double[] closeVal = new double[values.length];

        double max = -1.0e-300;
        double min = 1.0e300;

        for (int i = 0; i < values.length; i++) {
            meanVal[i] = values[i].meanValue;
            closeVal[i] = values[i].meanValue;
            high[i] = values[i].meanValue + values[i].stdErrMean;
            low[i] = values[i].meanValue - values[i].stdErrMean;

            min = Math.min(min, low[i]);
            max = Math.max(max, high[i]);
        }

        try {
            scd = new StockChartDataSet(high, "High", low, "Low", Color.black, new StockChartProperties());
            scd.setOpenValues(meanVal, "Open", Color.blue);
            scd.setCloseValues(closeVal, "Close", Color.blue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ds.addIAxisPlotDataSet(scd);
        AxisChart axisChart = new AxisChart(ds, new ChartProperties(), new AxisProperties(), null, graphWidth, graphHeight);

        // setting font
        AxisProperties axisProperties = new AxisProperties();
        ChartFont xScaleChartFont = new ChartFont(new Font("Lucida Console", Font.PLAIN, 8), Color.black);
        axisProperties.getXAxisProperties().setScaleChartFont(xScaleChartFont);

        //setting scale
        reScale(axisChart,min,max,1,0);

        return axisChart;
    }

    /**
     * Code taken from org.jCharts.encoders.BinaryEncoderUtil
     * @param chart
     * @return
     */
    protected static BufferedImage getImage(AxisChart chart)  {

        BufferedImage bufferedImage = null;

        //---if we use an ImageMap, we already have rendered the chart byt the time we get here so,
        //---	simply return the rendered image.
        if (chart.getGenerateImageMapFlag()) {
            bufferedImage = chart.getBufferedImage();
        } //---else, create a new BufferedImage and set the Graphics2D onto the chart.
        else {
            bufferedImage = new BufferedImage(chart.getImageWidth(), chart.getImageHeight(), BufferedImage.TYPE_INT_RGB);
            chart.setGraphics2D(bufferedImage.createGraphics());

            try {
                chart.render();
            } catch (Exception e)   {
                e.printStackTrace();
            }
        }

        return bufferedImage;
    }
}
