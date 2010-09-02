/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import DataStructure.Rx_DataSet;
import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.axisChart.*;
import org.jCharts.encoders.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 * Provides the functions for graphing
 * @author 20378332
 */
public class GraphFunctions {

    private static int numOfGraphs = 0;
    private static int graphWidth = 300;
    private static int graphHeight = 450;

    protected static void setSize(int w, int h) {
        graphWidth = w;
        graphHeight = h;
    }

    protected static BufferedImage readImage() {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("temp.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }

    protected static BufferedImage setSize(BufferedImage bi, int width, int height) {
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
        double lowerBound = (min + (max - min)/2) - (increment * (6 + offset));
        int numOfIncrements = 13;

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

        double max = -1000000000.0;
        double min = 1000000000.0;

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

        //setting scale
        reScale(axisChart,min,max,1,0);

        return axisChart;
    }

    protected static BufferedImage getImage(AxisChart axisChart)  {
        try {
            FileOutputStream os = new FileOutputStream(new File("temp.jpg"));
            JPEGEncoder.encode(axisChart, (float) 1.0, os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return readImage();
    }
}
