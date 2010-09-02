/***********************************************************************************************
 * File Info: $Id: ScatterPlotChartServlet.java,v 1.2 2003/03/14 03:23:23 nathaniel_auvil Exp $
 * Copyright (C) 2002
 * Author: Nathaniel G. Auvil
 * Contributor(s):
 *
 * Copyright 2002 (C) Nathaniel G. Auvil. All Rights Reserved.
 *
 * Redistribution and use of this software and associated documentation ("Software"), with or
 * without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright statements and notices.
 * 	Redistributions must also contain a copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * 	conditions and the following disclaimer in the documentation and/or other materials
 * 	provided with the distribution.
 *
 * 3. The name "jCharts" or "Nathaniel G. Auvil" must not be used to endorse or promote
 * 	products derived from this Software without prior written permission of Nathaniel G.
 * 	Auvil.  For written permission, please contact nathaniel_auvil@users.sourceforge.net
 *
 * 4. Products derived from this Software may not be called "jCharts" nor may "jCharts" appear
 * 	in their names without prior written permission of Nathaniel G. Auvil. jCharts is a
 * 	registered trademark of Nathaniel G. Auvil.
 *
 * 5. Due credit should be given to the jCharts Project (http://jcharts.sourceforge.net/).
 *
 * THIS SOFTWARE IS PROVIDED BY Nathaniel G. Auvil AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * jCharts OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 ************************************************************************************************/

package org.jCharts.demo.simpleServlet;


import org.jCharts.axisChart.ScatterPlotAxisChart;
import org.jCharts.chartData.ScatterPlotDataSeries;
import org.jCharts.chartData.ScatterPlotDataSet;
import org.jCharts.encoders.ServletEncoderHelper;
import org.jCharts.properties.*;
import org.jCharts.properties.util.ChartFont;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;


public class ScatterPlotChartServlet extends HttpServlet
{
	//---all of my charts serviced by this Servlet will have the same properties.
	protected LegendProperties legendProperties;
	protected AxisProperties axisProperties;
	protected ChartProperties chartProperties;

	protected int width = 550;
	protected int height = 360;


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void init()
	{
		this.legendProperties = new LegendProperties();
		this.chartProperties = new ChartProperties();
		this.axisProperties = new AxisProperties( true );
		ChartFont axisScaleFont = new ChartFont( new Font( "Georgia Negreta cursiva", Font.PLAIN, 13 ), Color.black );
		axisProperties.getXAxisProperties().setScaleChartFont( axisScaleFont );
		axisProperties.getYAxisProperties().setScaleChartFont( axisScaleFont );

		ChartFont axisTitleFont = new ChartFont( new Font( "Arial Narrow", Font.PLAIN, 14 ), Color.black );
		axisProperties.getXAxisProperties().setTitleChartFont( axisTitleFont );
		axisProperties.getYAxisProperties().setTitleChartFont( axisTitleFont );

		ChartFont titleFont = new ChartFont( new Font( "Georgia Negreta cursiva", Font.PLAIN, 14 ), Color.black );
		this.chartProperties.setTitleFont( titleFont );
	}


	/******************************************************************************************
	 *
	 *
	 ******************************************************************************************/
	private ScatterPlotProperties createScatterPlotProperties()
	{
		Stroke[] strokes = new Stroke[]{LineChartProperties.DEFAULT_LINE_STROKE};
		Shape[] shapes = new Shape[]{PointChartProperties.SHAPE_CIRCLE};

		return new ScatterPlotProperties( strokes, shapes );
	}


	/*****************************************************************************************
	 * Generates a random MultiDataSet
	 *
	 * @return ScatterPlotDataSet
	 ******************************************************************************************/
	private ScatterPlotDataSet createScatterPlotDataSet()
	{
		Point2D.Double[] points = new Point2D.Double[ 20 ];
		for( int x = 0; x < 20; x++ )
		{
			//--- y = x^2
			points[ x ] = ScatterPlotDataSet.createPoint2DDouble();
			points[ x ].setLocation( x, Math.pow( x, 2 ) );
		}

		ScatterPlotDataSet scatterPlotDataSet = new ScatterPlotDataSet( this.createScatterPlotProperties() );
		scatterPlotDataSet.addDataPoints( points, Color.red, "Proprietary" );

		return scatterPlotDataSet;
	}


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void service( HttpServletRequest req, HttpServletResponse httpServletResponse ) throws ServletException, IOException
	{
		try
		{
			ScatterPlotDataSet scatterPlotDataSet = this.createScatterPlotDataSet();
			ScatterPlotDataSeries scatterPlotDataSeries = new ScatterPlotDataSeries( scatterPlotDataSet,
																											 "X-Axis Title",
																											 "Y-Axis Title",
																											 "Chart Title" );

			DataAxisProperties xAxisProperties = new DataAxisProperties();
			xAxisProperties.setUserDefinedScale( -5, 3 );
			xAxisProperties.setNumItems( 10 );
			xAxisProperties.setRoundToNearest( 0 );

			DataAxisProperties yAxisProperties = new DataAxisProperties();
			yAxisProperties.setUserDefinedScale( -30, 50 );
			yAxisProperties.setNumItems( 10 );
			yAxisProperties.setRoundToNearest( 1 );

			AxisProperties axisProperties = new AxisProperties( xAxisProperties, yAxisProperties );
			ChartProperties chartProperties = new ChartProperties();
			LegendProperties legendProperties = new LegendProperties();

			ScatterPlotAxisChart scatterPlotAxisChart = new ScatterPlotAxisChart( scatterPlotDataSeries,
																										 chartProperties,
																										 axisProperties,
																										 legendProperties,
																										 500,
																										 400 );

			ServletEncoderHelper.encodeJPEG13( scatterPlotAxisChart, 1.0f, httpServletResponse );
		}
		catch( Throwable throwable )
		{
			//HACK do your error handling here...
			throwable.printStackTrace();
		}

	}
}
