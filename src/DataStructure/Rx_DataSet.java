/*
*  This file is part of S:SIMS.
*
*  S:SIMS is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  S:SIMS is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with S:SIMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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
