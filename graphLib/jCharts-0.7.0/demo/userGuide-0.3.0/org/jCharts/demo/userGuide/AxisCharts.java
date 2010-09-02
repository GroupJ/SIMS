
/***********************************************************************************************
* File Info: $Id: AxisCharts.java,v 1.3 2003/03/13 01:25:51 nathaniel_auvil Exp $
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
import org.jCharts.properties.util.ChartStroke;
import org.jCharts.properties.util.ChartFont;
import org.jCharts.axisChart.*;
import org.jCharts.test.TestDataGenerator;
import org.jCharts.types.ChartType;


/************************************************************************************
*
*
*************************************************************************************/
public class AxisCharts extends UserGuideBase
{
	public static final int width= 500;
	public static final int height= 300;


	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private AxisChart getChart( AxisProperties axisProperties ) throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );

		ChartProperties chartProperties= new ChartProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		return axisChart;
	}


	/*****************************************************************************************
	* Tests a 'real' data set and usage.
	*
	* @throws ChartDataException
	******************************************************************************************/
	public void run() throws ChartDataException, PropertyException
	{
		this.horizontalPlots();


		this.rounding();
		this.userDefinedScale();

		this.numberOfItemsOnYAxis();
		this.gridLines();

		this.dollarSigns();
		this.commas();

		this.axisBackgroundPaint();
		this.axisTitles();

		this.zeroLine();
		this.scaleFont();

		this.tickMarks();

		this.verticalXAxisLabels();
	}



	/*****************************************************************************************/
	private void horizontalPlots() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties( true );

		double[][] data= { { 3444d, 1506.3d, 2777d, 2550.345d, 659.667d, 950.6644d, 4500.3453d, 1200.67583d } };
		String[] xAxisLabels= { "January", "Febuary", "March", "April", "May", "June", "July", "August" };
		String[] legendLabels= { "New Bugs in Windows Per Month" };
		Paint[] paints= { Color.blue };
		String xAxisTitle= "Months";
		String yAxisTitle= "Bugs";
		String title= "Micro$oft At Work";

		DataSeries dataSeries= new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.BAR, new BarChartProperties() );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		AxisChart axisChart= new AxisChart( dataSeries, new ChartProperties(), axisProperties, new LegendProperties(), width, height );
		super.exportImage( axisChart, "horizontalPlots" );
	}




	/*****************************************************************************************/
	private void rounding() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();

		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setRoundToNearest( -2 );

		super.exportImage( this.getChart( axisProperties ), "axisRounding" );
	}




	/*****************************************************************************************/
	private void userDefinedScale() throws ChartDataException, PropertyException
	{
		AxisProperties axisProperties= new AxisProperties();

		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setUserDefinedScale( -2000, 850 );
		dataAxisProperties.setRoundToNearest( 0 );

		super.exportImage( this.getChart( axisProperties ), "userDefinedScale" );
	}

//todo what about ScaleCalculator implementations?

	/*****************************************************************************************/
	private void numberOfItemsOnYAxis() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setNumItems( 8 );

		super.exportImage( this.getChart( axisProperties ), "numberOfItemsOnYAxis" );
	}



	/*****************************************************************************************/
	private void gridLines() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();

		ChartStroke xAxisGridLines= new ChartStroke( new BasicStroke( 1.0f ), Color.red );
		axisProperties.getXAxisProperties().setGridLineChartStroke( xAxisGridLines );
		axisProperties.getXAxisProperties().setShowGridLines( AxisTypeProperties.GRID_LINES_ONLY_WITH_LABELS );

		axisProperties.getYAxisProperties().setShowGridLines( AxisTypeProperties.GRID_LINES_NONE );


		double[][] data= { { 3444d, 1506.3d, 2777d, 2550.345d, 659.667d, 950.6644d, 4500.3453d, 1200.67583d, 3000.4354d, 1268.0001d, 2444.432d, 5003d } };
		String[] xAxisLabels= { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		String[] legendLabels= { "New Bugs in Windows Per Month" };
		Paint[] paints= { Color.blue };
		String xAxisTitle= "Months";
		String yAxisTitle= "Bugs";
		String title= "Micro$oft At Work";

		DataSeries dataSeries= new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.AREA, new AreaChartProperties() );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		AxisChart axisChart= new AxisChart( dataSeries, new ChartProperties(), axisProperties, new LegendProperties(), width, height );
		super.exportImage( axisChart, "gridLines" );
	}




	/*****************************************************************************************/
	private void dollarSigns() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setUseDollarSigns( true );
		super.exportImage( this.getChart( axisProperties ), "dollarSigns" );
	}

	/*****************************************************************************************/
	private void commas() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setUseCommas( false );
		super.exportImage( this.getChart( axisProperties ), "commas" );
	}



	/*****************************************************************************************/
	private void axisBackgroundPaint() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		axisProperties.setBackgroundPaint( Color.yellow );
		super.exportImage( this.getChart( axisProperties ), "axisBackground" );
	}


	/*****************************************************************************************/
	private void axisTitles() throws ChartDataException
	{
		double[][] data= { { 3444d, 1506.3d, 2777d, 2550.345d, 659.667d, 950.6644d, 4500.3453d, 1200.67583d, 3000.4354d, 1268.0001d, 2444.432d, 5003d } };
		String[] xAxisLabels= { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		String[] legendLabels= { "New Bugs in Windows Per Month" };
		Paint[] paints= { Color.blue };
		String xAxisTitle= "Months";
		String yAxisTitle= "Bugs";
		String title= "Micro$oft At Work";
		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.AREA, new AreaChartProperties() );


		DataSeries dataSeries= new DataSeries( xAxisLabels, null, yAxisTitle, title );
		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		AxisChart axisChart= new AxisChart( dataSeries, new ChartProperties(), new AxisProperties(), new LegendProperties(), width, height );
		super.exportImage( axisChart, "axisTitles" );

		AxisProperties axisProperties= new AxisProperties();
		ChartFont yAxisFont= new ChartFont( new Font( "Arial Narrow", Font.BOLD, 14 ), Color.blue );
		axisProperties.getYAxisProperties().setAxisTitleChartFont( yAxisFont );

		axisChart= new AxisChart( dataSeries, new ChartProperties(), axisProperties, new LegendProperties(), width, height );
		super.exportImage( axisChart, "axisTitleFont" );
	}


	/*****************************************************************************************/
	private void zeroLine() throws ChartDataException, PropertyException
	{
		AxisProperties axisProperties= new AxisProperties();

		DataAxisProperties dataAxisProperties= (DataAxisProperties) axisProperties.getYAxisProperties();
		dataAxisProperties.setUserDefinedScale( -2000, 1200 );
		dataAxisProperties.setShowZeroLine( false );
		super.exportImage( this.getChart( axisProperties ), "noZeroLine" );

		dataAxisProperties.setShowZeroLine( true );

		BasicStroke stroke= new BasicStroke( 1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5f, new float[]{ 5f, 5f, 10f, 5f}, 4f );
		ChartStroke zeroLineChartStroke= new ChartStroke( stroke, Color.red );
		dataAxisProperties.setZeroLineChartStroke( zeroLineChartStroke );
		super.exportImage( this.getChart( axisProperties ), "zeroLinePaintStroke" );
	}



	/*****************************************************************************************/
	private void scaleFont() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		ChartFont xScaleChartFont= new ChartFont( new Font( "Georgia Negreta cursiva", Font.PLAIN, 13 ), Color.blue );
		axisProperties.getXAxisProperties().setScaleChartFont( xScaleChartFont );
		super.exportImage( this.getChart( axisProperties ), "scaleFont" );
	}


	/*****************************************************************************************/
	private void verticalXAxisLabels() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		axisProperties.setXAxisLabelsAreVertical( true );
		super.exportImage( this.getChart( axisProperties ), "verticalXAxisLabels" );
	}


	/*****************************************************************************************/
	private void tickMarks() throws ChartDataException
	{
		AxisProperties axisProperties= new AxisProperties();
		axisProperties.getXAxisProperties().setShowTicks( AxisTypeProperties.TICKS_ONLY_WITH_LABELS );
		super.exportImage( this.getChart( axisProperties ), "ticksWithLabels" );


		axisProperties= new AxisProperties();
		axisProperties.getXAxisProperties().setShowTicks( AxisTypeProperties.TICKS_NONE );
		super.exportImage( this.getChart( axisProperties ), "noTicks" );


		axisProperties= new AxisProperties();
		ChartStroke xTicks= new ChartStroke( new BasicStroke( 1.0f ), Color.blue );
		axisProperties.getXAxisProperties().setTickChartStroke( xTicks );

		ChartStroke yTicks= new ChartStroke( new BasicStroke( 1.0f ), Color.green );
		axisProperties.getYAxisProperties().setTickChartStroke( yTicks );

		super.exportImage( this.getChart( axisProperties ), "tickColors" );


		axisProperties= new AxisProperties();
		ChartStroke xTickStroke= new ChartStroke( new BasicStroke( 2.0f ), Color.blue );
		axisProperties.getXAxisProperties().setTickChartStroke( xTickStroke );
		super.exportImage( this.getChart( axisProperties ), "tickStroke" );
	}




	/*****************************************************************************************************
   *
	*
	*****************************************************************************************************/
	public static final DataSeries createDataSeries()
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= null;

		return new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, "Micro$oft At Work" );
	}

	/*****************************************************************************************
	*
	*
	* @param minValue
	* @param maxValue
	* @return AxisChartDataSet
	******************************************************************************************/
	public static final AxisChartDataSet createAxisChartDataSet(  ChartType chartType,
																			ChartTypeProperties chartTypeProperties,
																			int minValue,
																			int maxValue ) throws ChartDataException
	{
		double[][] data= TestDataGenerator.getRandomNumbers( 3, 7, minValue, maxValue );
		String[] legendLabels= { "Bugs", "Security Holes", "Backdoors" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 3 );

		return new AxisChartDataSet( data, legendLabels, paints, chartType, chartTypeProperties );
	}

}





