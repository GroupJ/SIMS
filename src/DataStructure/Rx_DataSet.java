/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataStructure;

/**
 * A container to hold mean and stderr values for graph.
 * @author 20378332
 */
public class Rx_DataSet {

    public double meanValue;
    public double stdErrMean;

    public Rx_DataSet(double meanValue, double stdErrMean)  {
        this.meanValue = meanValue;
        this.stdErrMean = stdErrMean;
    }
}
