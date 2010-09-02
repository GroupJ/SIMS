/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_summary;

import graph.*;
import DataStructure.*;
/**
 *
 * @author 20378332
 */
public class DSFunctions {

    public static void generateGraph(int[] tableIndex, int Rvalue)    {

        System.err.println(tableIndex.length + " " + Rvalue);

        String[] xAxisLabel = new String[tableIndex.length];
        for (int i = 0; i < tableIndex.length; i++)
            xAxisLabel[i] = "" + tableIndex[i];

        Rx_DataSet[] ds = new Rx_DataSet[tableIndex.length];
        for (int i = 0; i < tableIndex.length; i++) {

            double meanValue = Double.parseDouble((String)DSFrontEnd.tableModel.getValueAt(tableIndex[i], 7 + Rvalue*2));
            double stdErrMean = Double.parseDouble((String)DSFrontEnd.tableModel.getValueAt(tableIndex[i], 7 + Rvalue*2 + 1));
            ds[i] = new Rx_DataSet(meanValue, stdErrMean);
            
        }

        GraphFrontEnd.createGraph(xAxisLabel, ds, "File #", "R-" + Rvalue);
    }
}
