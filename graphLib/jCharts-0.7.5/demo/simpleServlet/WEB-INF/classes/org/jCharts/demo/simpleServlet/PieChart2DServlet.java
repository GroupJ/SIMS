/***********************************************************************************************
 * File Info: $Id: PieChart2DServlet.java,v 1.2 2003/03/14 03:23:23 nathaniel_auvil Exp $
 * Copyright (C) 2000
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


import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.PieChartDataSet;
import org.jCharts.encoders.ServletEncoderHelper;
import org.jCharts.nonAxisChart.PieChart2D;
import org.jCharts.properties.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.awt.*;
import java.io.IOException;


public class PieChart2DServlet extends HttpServlet
{
	//---all of my pie charts serviced by this Servlet will have the same properties.
	private PieChart2DProperties properties;
	private LegendProperties legendProperties;
	private ChartProperties chartProperties;

	private int width = 550;
	private int height = 350;


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void init()
	{
		//---all charts of this type of pie chart are going to share the same properties.
		this.properties = new PieChart2DProperties();

		this.legendProperties = new LegendProperties();
		this.legendProperties.setNumColumns( 2 );
		this.legendProperties.setPlacement( LegendProperties.RIGHT );

		this.chartProperties = new ChartProperties();
	}


	/**********************************************************************************************
	 *
	 **********************************************************************************************/
	public void service( HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException
	{
		try
		{
			PieChart2D pieChart2D = new PieChart2D( this.getData(), this.legendProperties, this.chartProperties, this.width, this.height );
			ServletEncoderHelper.encodeJPEG13( pieChart2D, 1.0f, response );
		}
		catch( Throwable throwable )
		{
			//HACK do your error handling here...
			throwable.printStackTrace();
		}
	}


	/******************************************************************************************
	 * Returns a Tests a 'real' data set and usage.
	 *
	 * @throws ChartDataException
	 ******************************************************************************************/
	private PieChartDataSet getData() throws ChartDataException
	{
		double[] data = new double[]{40, 15, 35, 65, 59};
		Paint[] paints = new Paint[]{Color.blue, Color.red, Color.green, Color.yellow, Color.white};
		String[] labels = {"BMW", "Honda", "Lexus", "Audi", "Acura"};
		return new PieChartDataSet( "Cars That Own!", data, labels, paints, this.properties );
	}
}
