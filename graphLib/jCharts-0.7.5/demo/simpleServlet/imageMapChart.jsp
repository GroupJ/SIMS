
<%@page import="org.jCharts.demo.simpleServlet.ChartServlet" %>
<%@page import="org.jCharts.imageMap.*" %>
<%@page import="java.util.Iterator" %>


<%
/**************************************************************************************
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
**********************************************************************************/

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>jCharts - Client-side Image Map Demo</title>

<script language="JavaScript">


/*******************************************************************/
function showValues( value, legendLabel, xAxisLabel )
{
	if( xAxisLabel != 'null' )
	{
		alert( "value= " + value + "\nlegend label= " + legendLabel + "\naxis label= " + xAxisLabel );
	}
	else
	{
		alert( "value= " + value + "\nlegend label= " + legendLabel );
	}
}

</script>

</head>

<body leftmargin=5 topmargin=0>


<table width="100%" bgcolor="Black">
<tr>
	<td><img src="jChartsTitle.png" width=200 height=65 border=0 alt=""></td>
</tr>
<tr>
	<td style="font-family: 'Arial Narrow'; color: White; font-size: 14pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client-side Image Map Demo</td>
</tr>
</table>


<br/><br/>


<table border="1" cellspacing="10" cellpadding="10">
<tr>
	<td bgcolor="Silver"><img border="0" src="ChartServlet" useMap="#chartMap"></td>
</tr>
</table>


<map name="chartMap">

<%
StringBuffer html= new StringBuffer( 100 );
ImageMap imageMap= (ImageMap) request.getAttribute( ChartServlet.IMAGE_MAP );

Iterator iterator= imageMap.getIterator();
while( iterator.hasNext() )
{
	ImageMapArea imageMapArea= (ImageMapArea) iterator.next();
	
	html.append( "href=\"javascript:showValues(" );
	html.append( imageMapArea.getValue() );
	html.append( ",'" );
	html.append( imageMapArea.getLengendLabel() );
	html.append( "','" );
	html.append( imageMapArea.getXAxisLabel() );
	html.append( "');\"" );
%>
	<%= 	imageMapArea.toHTML( html.toString() ) %>
<% 
	//---reuse same StringBuffer Object
	html.delete( 0, html.length() );
} 
%>
</map>

<br/><br/>
Click on the chart to see the values.<br/><br/>

This is a trivial example of what is possible.  
For example, you could create a <b>mouseOver</b> function to show the values when the mouse simply moves over the chart. 
Or, you could use this to drill down into charts.  Or you could link to other pages...  There are many things one could do...
<br/><br/>

I will leave the rest to your imagination and your html coder...

</body>
</html>
