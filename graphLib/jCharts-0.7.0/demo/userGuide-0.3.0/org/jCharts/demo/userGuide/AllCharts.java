
/***********************************************************************************************
* File Info: $Id: AllCharts.java,v 1.2 2003/03/12 03:19:37 nathaniel_auvil Exp $
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

import org.jCharts.axisChart.AxisChart;
import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.DataSeries;
import org.jCharts.encoders.PNGEncoder;
import org.jCharts.properties.*;
import org.jCharts.properties.util.ChartStroke;
import org.jCharts.types.ChartType;

import java.awt.*;
import java.io.FileOutputStream;


/************************************************************************************
*
*
*************************************************************************************/
public class AllCharts extends UserGuideBase
{
	private DataSeries dataSeries;



	public AllCharts() throws ChartDataException
	{
		this.dataSeries= AxisCharts.createDataSeries();
		this.dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );
	}


	/*****************************************************************************************
	* Tests a 'real' data set and usage.
	*
	* @throws Throwable
	******************************************************************************************/
	public void run() throws Throwable
	{
		this.exportImage();
		this.backgroundPaint();
		this.chartBorder();
		this.edgePadding();
		this.chartTitle();
      this.chartTitleWrapping();
	}



	/********************************************************************************************/
	private void exportImage() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		FileOutputStream fileOutputStream= new FileOutputStream( "exportPng.png" );
		PNGEncoder.encode( axisChart, fileOutputStream );
		fileOutputStream.flush();
		fileOutputStream.close();
	}


	/********************************************************************************************/
	private void backgroundPaint() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		chartProperties.setBackgroundPaint( Color.gray );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "backgroundPaint" );
	}



	/*******************************************************************************************/
	private void chartBorder() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();

		ChartStroke borderStroke= new ChartStroke( new BasicStroke( 1.0f ), Color.black );
		chartProperties.setBorderStroke( borderStroke );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "chartBorder" );
	}


	/********************************************************************************************/
	private void edgePadding() throws Throwable
	{
		ChartProperties chartProperties= new ChartProperties();
		chartProperties.setEdgePadding( 20f );

		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( this.dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );


		super.exportImage( axisChart, "edgePadding" );
	}


	/********************************************************************************************/
	private void chartTitle() throws Throwable
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		//String title= null;
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, null );

		dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "chartTitle" );
	}


	/********************************************************************************************/
	private void chartTitleWrapping() throws Throwable
	{
		String[] xAxisLabels= { "1998", "1999", "2000", "2001", "2002", "2003", "2004" };
		String xAxisTitle= "Years";
		String yAxisTitle= "Problems";
		//String title= null;
		DataSeries dataSeries = new DataSeries( xAxisLabels, xAxisTitle, yAxisTitle, "This title is so very, very, very, very, very, very, very, very, very, very, long it is going to wrap to the next line" );

		dataSeries.addIAxisPlotDataSet( AxisCharts.createAxisChartDataSet( ChartType.AREA, new AreaChartProperties(), 200, 5000 ) );

		ChartProperties chartProperties= new ChartProperties();
		AxisProperties axisProperties= new AxisProperties();
		LegendProperties legendProperties= new LegendProperties();

		AxisChart axisChart= new AxisChart( dataSeries, chartProperties, axisProperties, legendProperties, AxisCharts.width, AxisCharts.height );

		super.exportImage( axisChart, "chartTitleWrapping" );
	}

}





