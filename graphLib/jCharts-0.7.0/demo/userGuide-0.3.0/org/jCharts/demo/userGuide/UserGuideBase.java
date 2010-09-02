
/***********************************************************************************************
* File Info: $Id: UserGuideBase.java,v 1.2 2003/03/13 01:54:00 nathaniel_auvil Exp $
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


import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;

import org.jCharts.Chart;
import org.jCharts.encoders.PNGEncoder;



abstract class UserGuideBase
{

	/*******************************************************************************************
	*
	*
	******************************************************************************************/
	public static void main( String[] args ) throws Throwable
	{
		AllCharts allCharts= new AllCharts();
		//allCharts.run();

		Legends legends= new Legends();
		//legends.run();

      StockCharts stockCharts= new StockCharts();
		//stockCharts.run();


		PieChart pieChart= new PieChart();
		//pieChart.run();

		AxisCharts axisCharts= new AxisCharts();
		//axisCharts.run();

		AreaCharts areaCharts= new AreaCharts();
		//areaCharts.run();

		BarCharts barCharts= new BarCharts();
		barCharts.run();

		LineCharts lineCharts= new LineCharts();
		//lineCharts.run();

		PointCharts pointCharts= new PointCharts();
		//pointCharts.run();

		ComboCharts comboCharts= new ComboCharts();
		//comboCharts.run();

	}


	abstract void run() throws Throwable;


	/******************************************************************************************
	* Utility method to write the image to file so I do not have to deal with file i/o
	*  every time I write a demo.
	*
	* @param chart everything that is renderable extends this class.
	* @param fileName what to name the file
	******************************************************************************************/
	void exportImage( Chart chart, String fileName )
	{
	   String extension= ".png";
		FileOutputStream fileOutputStream;

		try
		{
			fileOutputStream= new FileOutputStream( fileName + extension );
			PNGEncoder.encode( chart, fileOutputStream );
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		catch( Throwable throwable )
		{
			throwable.printStackTrace();
		}
	}
}