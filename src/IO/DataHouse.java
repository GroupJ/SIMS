/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

/**
 *
 * @author 20378332
 */

import java.util.*;

public class DataHouse {

    public String fileName;
    public String absolutePath;
    public String system;
    public String date;
    public String time;
    // ACQUISITION FILE NAME
    public String ACFN;
    // CONDITION FILE NAME
    public String CFN;
    // File Description
    public String FD;
    // SAMPLE NAME
    public String SampleName;
    // X POSITION
    public int x_pos;
    // Y POSITION
    public int y_pos;
    // ACQUISITION PARAMETERS
    public ArrayList<String> AQP_col_title = new ArrayList<String>();
    public ArrayList<String> AQP_row_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> AQP_Grid = new ArrayList<ArrayList<String>>();
    // ANALYTICAL PARAMETERS
    public ArrayList<String> AP_title = new ArrayList<String>();
    public ArrayList<String> AP_value = new ArrayList<String>();
    // COMMENTS
    public String comments;
    // DETECTOR PARAMETERS
    public ArrayList<String> DTP_col_title = new ArrayList<String>();
    public ArrayList<String> DTP_row_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> DTP_Grid = new ArrayList<ArrayList<String>>();
    // CORRECTION FACTORS FOR RATIOS COMPUTATION
    public ArrayList<String> CF_title = new ArrayList<String>();
    public ArrayList<String> CF_value = new ArrayList<String>();
    // ACQUISITION CONTROL PARAMETERS
    public ArrayList<String> ACP_title = new ArrayList<String>();
    public ArrayList<String> ACP_value = new ArrayList<String>();
    // Pre-sputtering PARAMETERS
    public ArrayList<String> sputP_title = new ArrayList<String>();
    public ArrayList<String> sputP_value = new ArrayList<String>();
    // ISOTOPICS RATIO
    public ArrayList<String> IR = new ArrayList<String>();
    // CUMULATED RESULTS
    public ArrayList<String> CR_col_title = new ArrayList<String>();
    public ArrayList<String> CR_row_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> CR_Grid = new ArrayList<ArrayList<String>>();
    // PRIMARY RESULTS
    public double PR_start;
    public double PR_end;
    // BEAM CENTERING RESULTS
    public ArrayList<String> BCR_col_title = new ArrayList<String>();
    public ArrayList<String> BCR_row_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> BCR_Grid = new ArrayList<ArrayList<String>>();
    // FIELD RECENTERING (ppm)
    public ArrayList<String> FR_col_title = new ArrayList<String>();
    public ArrayList<String> FR_row_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> FR_Grid = new ArrayList<ArrayList<String>>();
    // RAW DATA
    public ArrayList<String> RD_col_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> RD_Grid = new ArrayList<ArrayList<String>>();
    // PRIMARY INTENSITY DATA
    public ArrayList<String> PID_col_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> PID_Grid = new ArrayList<ArrayList<String>>();
    // TIMING  DATA
    public ArrayList<String> TD_col_title = new ArrayList<String>();
    public ArrayList<ArrayList<String>> TD_Grid = new ArrayList<ArrayList<String>>();

    public DataHouse() {
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("System : " + system + "\tdate : " + date + "\ttime : " + time + "\n");
        sb.append("Acquisition File Name : " + ACFN + "\n");
        sb.append("Condition File Name : " + CFN + "\n");
        sb.append("File Description : " + FD + "\n");
        sb.append("Sample Name : " + SampleName + "\n");
        sb.append("xpos : " + x_pos + "\ty : " + y_pos + "\n\n\n");
        sb.append("Acquisiton Parameters :\n");

        sb.append("\t");
        for (int i = 0; i < AQP_col_title.size(); i++) {
            sb.append(AQP_col_title.get(i) + "\t");
        }
        sb.append("\n");

        for (int i = 0; i < AQP_row_title.size(); i++) {
            sb.append(AQP_row_title.get(i) + "\t");
            for (int j = 0; j < AQP_Grid.get(i).size(); j++) {
                sb.append(AQP_Grid.get(i).get(j) + "\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nAnalytical Parameters :\n");
        for (int i = 0; i < AP_title.size(); i++) {
            if (AP_title.get(i).startsWith("Max Area"))
                sb.append(AP_title.get(i) + "\t" + AP_value.get(i) + "\n");
            else
                sb.append(AP_title.get(i) + "\t\t" + AP_value.get(i) + "\n");
        }

        sb.append("\n\nComments: \t" + comments + "\n");
        sb.append("\n\nDetector Parameters : \n");

        sb.append("\t");
        for (int i = 0; i < DTP_col_title.size(); i++) {
            sb.append(DTP_col_title.get(i) + "\t");
        }
        sb.append("\n");

        for (int i = 0; i < DTP_row_title.size(); i++) {
            sb.append(DTP_row_title.get(i) + "\t");
            for (int j = 0; j < DTP_Grid.get(i).size(); j++) {
                sb.append(DTP_Grid.get(i).get(j) + "\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nCorrection Factors :\n");
        for (int i = 0; i < CF_title.size(); i++) {
            if (CF_title.get(i).startsWith("Dead Time"))
                sb.append(CF_title.get(i) + "\t" + CF_value.get(i) + "\n");
            else if (CF_title.get(i).startsWith("Ip Normalize"))
                sb.append(CF_title.get(i) + "\t\t" + CF_value.get(i) + "\n");
            else
                sb.append(CF_title.get(i) + "\t\t\t" + CF_value.get(i) + "\n");
        }

        sb.append("\n\nAcquisition Control Parameters :\n");
        for (int i = 0; i < ACP_title.size(); i++) {
            if (ACP_title.get(i).startsWith("Pre-sputtering"))
                sb.append(ACP_title.get(i) + "" + ACP_value.get(i) + "\n");
            else
                sb.append(ACP_title.get(i) + "\t" + ACP_value.get(i) + "\n");
        }

        if (sputP_title.size() != 0) {
            sb.append("Pre - Sputtering Parameters :\n");
            for (int i = 0; i < sputP_title.size(); i++) {
                sb.append(sputP_title.get(i) + "\t" + sputP_value.get(i) + "\n");
            }
        }

        sb.append("\n\nIsotopic Ratio :\n");
        for (int i = 0; i < IR.size(); i++) {
            sb.append(IR.get(i) + "\n");
        }

        sb.append("\n\nCumulated Results :\n");
        sb.append("\t");
        for (int i = 0; i < CR_col_title.size(); i++) {
            sb.append(CR_col_title.get(i) + "\t");
        }
        sb.append("\n");

        for (int i = 0; i < CR_row_title.size(); i++) {
            sb.append(CR_row_title.get(i) + "\t");
            for (int j = 0; j < CR_Grid.get(i).size(); j++) {
                if (j < 0)
                    sb.append(CR_Grid.get(i).get(j) + "\t");
                else
                    sb.append(CR_Grid.get(i).get(j) + "\t\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nPrimary Current :\tStart : " + PR_start + "\tEnd : " + PR_end + "\n");

        sb.append("\n\nBeam Centering Result :\n");
        sb.append("\t\t\t");
        for (int i = 0; i < BCR_col_title.size(); i++) {
            sb.append(BCR_col_title.get(i) + "\t");
        }
        sb.append("\n");

        for (int i = 0; i < BCR_row_title.size(); i++) {
            if (BCR_row_title.get(i).startsWith("Option"))
                sb.append(BCR_row_title.get(i) + "\t");
            else
                sb.append(BCR_row_title.get(i) + "\t\t");
            
            for (int j = 0; j < BCR_Grid.get(i).size(); j++) {
                sb.append(BCR_Grid.get(i).get(j) + "\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nRaw Data :\n");
        for (int i = 0; i < RD_col_title.size(); i++) {
            if (i < 2)
                sb.append(RD_col_title.get(i) + "\t");
            else
                sb.append(RD_col_title.get(i) + "\t\t");
        }
        sb.append("\n");

        for (int i = 0; i < RD_Grid.size(); i++) {
            for (int j = 0; j < RD_Grid.get(i).size(); j++) {
                if (j < 2)
                    sb.append(RD_Grid.get(i).get(j) + "\t");
                else
                    sb.append(RD_Grid.get(i).get(j) + "\t\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nPrimary Intensity Data :\n");
        for (int i = 0; i < PID_col_title.size(); i++) {
            if (i < 2)
                sb.append(PID_col_title.get(i) + "\t");
            else
                sb.append(PID_col_title.get(i) + "\t\t");
        }
        sb.append("\n");

        for (int i = 0; i < PID_Grid.size(); i++) {
            for (int j = 0; j < PID_Grid.get(i).size(); j++) {
                if (j < 2)
                    sb.append(PID_Grid.get(i).get(j) + "\t");
                else
                    sb.append(PID_Grid.get(i).get(j) + "\t\t");
            }
            sb.append("\n");
        }

        sb.append("\n\nTiming Data :\n");
        for (int i = 0; i < TD_col_title.size(); i++) {
            if (i < 2)
                sb.append(TD_col_title.get(i) + "\t");
            else
                sb.append(TD_col_title.get(i) + "\t\t");
        }
        sb.append("\n");

        for (int i = 0; i < TD_Grid.size(); i++) {
            for (int j = 0; j < TD_Grid.get(i).size(); j++) {
                if (j < 2)
                    sb.append(TD_Grid.get(i).get(j) + "\t");
                else
                    sb.append(TD_Grid.get(i).get(j) + "\t\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
