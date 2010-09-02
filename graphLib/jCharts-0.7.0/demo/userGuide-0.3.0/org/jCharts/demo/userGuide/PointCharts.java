
/***********************************************************************************************
* File Info: $Id: PointCharts.java,v 1.2 2003/03/13 02:37:28 nathaniel_auvil Exp $
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
public class PointCharts extends AxisCharts
{

	/*****************************************************************************************/
	public PointCharts() throws Throwable
	{
		super();
	}


	/*****************************************************************************************
	* Tests a 'real' data set and usage.
	*
	* @throws ChartDataException
	******************************************************************************************/
	public void run() throws ChartDataException
	{
		this.basicChart();
		this.shapes();
		this.nullValues();
	}


	/*****************************************************************************************
	*
	*
	******************************************************************************************/
	private void basicChart() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );

		double[][] data= new double[][]{ { 250, 45, -36, 66, 145, 80, 55  }, { 150, 15, 6, 62, -54, 10, 84  } };
		String[] legendLabels= { "Bugs", "Security Holes" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 2 );

		Shape[] shapes= { PointChartProperties.SHAPE_DIAMOND, PointChartProperties.SHAPE_TRIANGLE };
		boolean[] fillPointFlags= { true, true };
		Paint[] outlinePaints= { Color.black, Color.black };
		PointChartProperties pointChartProperties= new PointChartProperties( shapes, fillPointFlags, outlinePaints );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.POINT, pointChartProperties );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "basicChart" );
	}



	/******************************************************************************************/
	private void shapes() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );


		double[][] data= TestDataGenerator.getRandomNumbers( 1, 7, 0, 6000 );
		String[] legendLabels= { "Bugs" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 1 );

		Shape[] shapes= { PointChartProperties.SHAPE_CIRCLE };
		boolean[] fillPointFlags= { false };
		Paint[] outlinePaints= { Color.red };
		PointChartProperties pointChartProperties= new PointChartProperties( shapes, fillPointFlags, outlinePaints );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.POINT, pointChartProperties );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "shapes" );
	}


	/******************************************************************************************/
	private void nullValues() throws ChartDataException
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		String title= "Micro$oft at Work";
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, title );


		double[][] data= TestDataGenerator.getRandomNumbers( 1, 7, 0, 6000 );
		data[ 0 ][ 3 ]= Double.NaN;
		data[ 0 ][ 4 ]= Double.NaN;

		String[] legendLabels= { "Bugs" };
		Paint[] paints= TestDataGenerator.getRandomPaints( 1 );

		Shape[] shapes= { PointChartProperties.SHAPE_CIRCLE };
		boolean[] fillPointFlags= { true };
		Paint[] outlinePaints= { Color.black };
		PointChartProperties pointChartProperties= new PointChartProperties( shapes, fillPointFlags, outlinePaints );

		AxisChartDataSet axisChartDataSet= new AxisChartDataSet( data, legendLabels, paints, ChartType.POINT, pointChartProperties );

		dataSeries.addIAxisPlotDataSet( axisChartDataSet );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "nullValues" );
	}

}

