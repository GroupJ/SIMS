
/***********************************************************************************************
* File Info: $Id: StockCharts.java,v 1.1.1.1 2002/07/25 02:29:36 nathaniel_auvil Exp $
* Copyright (C) 2000
* Author: Nathaniel G. Auvil
* Contributor(s):
*
* Copyright 2002 (C) Nathaniel G. Auvil. All Rights Reserved.
*
* Redistribution and use of this software and associated documentation
* ("Software"), with or without modification, are permitted provided
* that the following conditions are met:
*
* 1. Redistributions of source code must retain copyright
*    statements and notices.  Redistributions must also contain a
*    copy of this document.
*
* 2. Redistributions in binary form must reproduce the
*    above copyright notice, this list of conditions and the
*    following disclaimer in the documentation and/or other
*    materials provided with the distribution.
*
* 3. The name "jCharts" or "Nathaniel G. Auvil" must not be used to
* 	  endorse or promote products derived from this Software without
* 	  prior written permission of Nathaniel G. Auvil.  For written
*    permission, please contact nathaniel_auvil@users.sourceforge.net
*
* 4. Products derived from this Software may not be called "jCharts"
*    nor may "jCharts" appear in their names without prior written
*    permission of Nathaniel G. Auvil. jCharts is a registered
*    trademark of Nathaniel G. Auvil.
*
* 5. Due credit should be given to the jCharts Project
*    (http://jcharts.sourceforge.net/).
*
* THIS SOFTWARE IS PROVIDED BY Nathaniel G. Auvil AND CONTRIBUTORS
* ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
* NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
* jCharts OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
* INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
* HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
* STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
* OF THE POSSIBILITY OF SUCH DAMAGE.
************************************************************************************************/

package org.jCharts.demo.userGuide;

import java.awt.*;

import org.jCharts.chartData.*;
import org.jCharts.properties.*;
import org.jCharts.axisChart.*;
import org.jCharts.types.ChartType;
import org.jCharts.test.TestDataGenerator;


/************************************************************************************
*
*
*************************************************************************************/
public class StockCharts extends AxisCharts
{

	/*****************************************************************************************/
	public StockCharts() throws Throwable
	{
		super();
	}


	/*****************************************************************************************
	*
	*
	* @throws ChartDataException
	******************************************************************************************/
	public void run() throws ChartDataException
	{
		this.basicChart();
		this.strokes();
		this.lineLengths();
	}


	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private void basicChart() throws ChartDataException
	{
		String[] xAxisLabels= { "June 2", "June 3", "June 4", "June 5", "June 6", "June 7", "June 8" };
		String xAxisTitle= "Days";
		String yAxisTitle= "$$$";
		String title= "Id Software";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		int dataSize= xAxisLabels.length;
      int numberOfDataSets= 1;


      StockChartDataSet stockChartDataSet;

		double[] highs= TestDataGenerator.getRandomNumbers( dataSize, 500, 1000 );
		double[] lows= TestDataGenerator.getRandomNumbers( dataSize, 100, 300 );
		double[] opens= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );
		double[] closes= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );

      StockChartProperties stockChartProperties= new StockChartProperties();

		stockChartDataSet= new StockChartDataSet( highs, "High", lows, "Low", Color.black, stockChartProperties );
		stockChartDataSet.setOpenValues( opens, "Open", Color.red );
		stockChartDataSet.setCloseValues( closes, "Close", Color.green );

		dataSeries.addIAxisPlotDataSet( stockChartDataSet );



		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "basicChart" );
	}



	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private void strokes() throws ChartDataException
	{
		String[] xAxisLabels= { "June 2", "June 3", "June 4", "June 5", "June 6", "June 7", "June 8" };
		String xAxisTitle= "Days";
		String yAxisTitle= "$$$";
		String title= "Id Software";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		int dataSize= xAxisLabels.length;
      int numberOfDataSets= 1;


      StockChartDataSet stockChartDataSet;

		double[] highs= TestDataGenerator.getRandomNumbers( dataSize, 500, 1000 );
		double[] lows= TestDataGenerator.getRandomNumbers( dataSize, 100, 300 );
		double[] opens= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );
		double[] closes= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );

      StockChartProperties stockChartProperties= new StockChartProperties();
		stockChartProperties.setHiLowStroke( new BasicStroke( 3.0f ) );
		stockChartProperties.setCloseStroke( new BasicStroke( 2.5f ) );

		stockChartDataSet= new StockChartDataSet( highs, "High", lows, "Low", Color.black, stockChartProperties );
		stockChartDataSet.setOpenValues( opens, "Open", Color.red );
		stockChartDataSet.setCloseValues( closes, "Close", Color.green );

		dataSeries.addIAxisPlotDataSet( stockChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "strokes" );
	}


	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private void lineLengths() throws ChartDataException
	{
		String[] xAxisLabels= { "June 2", "June 3", "June 4", "June 5", "June 6", "June 7", "June 8" };
		String xAxisTitle= "Days";
		String yAxisTitle= "$$$";
		String title= "Id Software";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		int dataSize= xAxisLabels.length;
      int numberOfDataSets= 1;


      StockChartDataSet stockChartDataSet;

		double[] highs= TestDataGenerator.getRandomNumbers( dataSize, 500, 1000 );
		double[] lows= TestDataGenerator.getRandomNumbers( dataSize, 100, 300 );
		double[] opens= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );
		double[] closes= TestDataGenerator.getRandomNumbers( dataSize, 350, 450 );

      StockChartProperties stockChartProperties= new StockChartProperties();
		stockChartProperties.setOpenPixelLength( 10 );
		stockChartProperties.setClosePixelLength( 20 );

		stockChartDataSet= new StockChartDataSet( highs, "High", lows, "Low", Color.black, stockChartProperties );
		stockChartDataSet.setOpenValues( opens, "Open", Color.red );
		stockChartDataSet.setCloseValues( closes, "Close", Color.green );

		dataSeries.addIAxisPlotDataSet( stockChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "pixelLengths" );
	}

}

