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
package IO;

import DataStructure.DataHouse;
import java.io.*;
import java.util.*;

/**
 * 1 Reads/Parse Input Files from Cameca 1280.
 * 2 Stores Data
 * 3 Prints to standard error
 * @author Ragil Prasetya 12:53am 17th AUG
 */
public class ReadInput {

    private static BufferedReader br;
    private static DataHouse dh;
    private static final String[] sections = {"ACQUISITION PARAMETERS",
        "ANALYTICAL PARAMETERS",
        "DETECTOR PARAMETERS",
        "COMMENTS",
        "CORRECTION FACTORS FOR RATIOS COMPUTATION",
        "ACQUISITION CONTROL PARAMETERS",
        "Pre-sputtering PARAMETERS",
        "ISOTOPICS RATIO",
        "CUMULATED RESULTS",
        "PRIMARY : ===",
        "BEAM CENTERING RESULTS",
        "RAW DATA",
        "PRIMARY INTENSITY DATA",
        "TIMING  DATA "};

    private static void callReadFunc(int index) throws Exception {
        switch (index) {
            case 0:
                readAQP();
                return;
            case 1:
                readAP();
                return;
            case 2:
                readDTP();
                return;
            case 3:
                readComments();
                return;
            case 4:
                readCF();
                return;
            case 5:
                readACP();
                return;
            case 6:
                readsputP();
                return;
            case 7:
                readIR();
                return;
            case 8:
                readCR();
                return;
            case 9:
                readPR();
                return;
            case 10:
                readBCR();
                return;
            case 11:
                readRD();
                return;
            case 12:
                readPID();
                return;
            case 13:
                readTD();
                return;
        }
    }

    /**
     * Reads a file and returns a Datahouse.
     * @param file the file to read
     * @return DataHouse info of the file
     * @throws Exception If any IO errors occur. Currently unhandled.
     */
    public static DataHouse readInput(File file) throws Exception   {

        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        readInput();
        br.close();

        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        readUnformated();
        br.close();

        dh.fileName = file.getName();
        dh.absolutePath = file.getAbsolutePath();
        cleanDataHouse(dh);
        return dh;
    }

    private static void readUnformated() throws Exception   {
        String temp = br.readLine();
        StringBuffer buffer = new StringBuffer();
        while (temp != null)    {
            buffer.append(temp + "\n");
            temp = br.readLine();
        }
        dh.unformattedContent = buffer.toString();
    }

    private static void readInput() throws Exception {

        dh = new DataHouse();

        readHeader();
        readSampleName();

        String buffer = skipBlankLines();
        while (buffer != null) {

            //System.err.println("Heading Found : " + buffer);

            for (int i = 0; i < sections.length; i++) {
                if (buffer.indexOf(sections[i]) != -1) {
                    callReadFunc(i);
                    break;
                }
            }
            buffer = skipBlankLines();
        }

        //System.err.println("Success\n\n\n");
        //System.err.println(dh.toString());
    }

    private static String skipBlankLines() throws Exception {
        String buffer = br.readLine();
        if (buffer == null) {
            return null;
        }

        while (!(new StringTokenizer(buffer)).hasMoreElements()) {
            buffer = br.readLine();
            if (buffer == null) {
                return null;
            }
        }
        return buffer;
    }

    private static void readHeader() throws Exception {

        System.err.println("Reading Header");

        String buffer;
        String[] tokens;

        // read the first line
        buffer = br.readLine();
        StringTokenizer st = new StringTokenizer(buffer);
        dh.system = st.nextToken();
        dh.date = st.nextToken();

        // read the second line
        buffer = br.readLine();
        st = new StringTokenizer(buffer);
        dh.time = st.nextToken() + " " + st.nextToken();

        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        dh.ACFN = tokens[1];
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        dh.CFN = tokens[1];

        dh.FD = skipBlankLines();
        dh.FD = skipBlankLines();

        skipBlankLines();
    }

    private static void readSampleName() throws Exception {

        System.err.println("Reading Sample Name");

        String buffer;
        String[] tokens;

        // read sample name
        buffer = br.readLine();
        tokens = buffer.split("\\t");
        dh.SampleName = tokens[1];

        // read x-y pos
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        dh.x_pos = Integer.parseInt(tokens[1]);
        dh.y_pos = Integer.parseInt(tokens[3]);

    }

    private static void readAQP() throws Exception {

        System.err.println("Reading ACQUISITION PARAMETERS");

        String buffer;
        String[] tokens;
        // read any blank lines;
        buffer = skipBlankLines();

        // process column titles;
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.AQP_col_title.add(tokens[i]);
            }
        }

        // skip blank lines
        buffer = br.readLine();
        while (buffer.split("\\s").length == 1) {
            buffer = br.readLine();
        }

        // process all rows;
        int row_counter = 0;
        while ((tokens = buffer.split("\t")).length != 1) {
            dh.AQP_row_title.add(tokens[0]);
            dh.AQP_Grid.add(new ArrayList<String>());
            for (int i = 1; i < tokens.length; i++) {
                dh.AQP_Grid.get(row_counter).add(tokens[i]);
            }
            row_counter++;
            buffer = br.readLine();
        }
    }

    private static void readAP() throws Exception {

        System.err.println("Reading ANALYTICAL PARAMETERS");

        String buffer = skipBlankLines();
        String tokens[];

        while ((tokens = buffer.split("\\t")).length != 1) {
            dh.AP_title.add(tokens[0]);
            dh.AP_value.add(tokens[1]);

            buffer = br.readLine();
        }
    }

    private static void readComments() throws Exception {

        System.err.println("Reading COMMENTS ");

        dh.comments = br.readLine();
    }

    private static void readDTP() throws Exception {

        System.err.println("Reading DETECTOR PARAMETERS");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.DTP_col_title.add(tokens[i]);
            }
        }

        int row_counter = 0;
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        while (tokens.length != 1) {
            dh.DTP_row_title.add(tokens[0]);
            dh.DTP_Grid.add(new ArrayList<String>());
            for (int i = 1; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.DTP_Grid.get(row_counter).add(tokens[i]);
                }
            }
            buffer = br.readLine();
            tokens = buffer.split(("\\t"));
            row_counter++;
        }
    }

    private static void readCF() throws Exception {

        System.err.println("Reading CORRECTION FACTORS FOR RATIOS COMPUTATION");

        String buffer = skipBlankLines();
        String tokens[];

        while ((tokens = buffer.split("\\:")).length != 1) {
            dh.CF_title.add(tokens[0]);
            dh.CF_value.add(tokens[1]);

            buffer = br.readLine();
        }
    }

    private static void readACP() throws Exception {

        System.err.println("Reading ACQUISITION CONTROL PARAMETERSS");

        String buffer = skipBlankLines();
        String tokens[];

        tokens = buffer.split("\\:");

        while (tokens.length != 1) {
            dh.ACP_title.add(tokens[0]);
            dh.ACP_value.add(tokens[1]);

            buffer = br.readLine();
            tokens = buffer.split("\\t");
        }
    }

    private static void readsputP() throws Exception {

        System.err.println("Reading Pre-sputtering PARAMETERS");

        String buffer = skipBlankLines();
        String tokens[];

        while ((tokens = buffer.split("\\t")).length != 1) {
            dh.sputP_title.add(tokens[0]);
            dh.sputP_value.add(tokens[1]);

            buffer = br.readLine();
        }
    }

    private static void readIR() throws Exception {

        System.err.println("Reading ISOTOPICS RATIO");

        String buffer = skipBlankLines();
        do {
            dh.IR.add(buffer);
            buffer = br.readLine();
            buffer = br.readLine();
        } while (buffer.split("\\s").length != 1);
    }

    private static void readCR() throws Exception {

        System.err.println("Reading CUMULATED RESULTS");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.CR_col_title.add(tokens[i]);
            }
        }

        buffer = skipBlankLines();
        for (int j = 0; j < dh.IR.size(); j++) {
            tokens = buffer.split("\\t");
            dh.CR_row_title.add(tokens[0]);
            dh.CR_Grid.add(new ArrayList<String>());

            for (int i = 1; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.CR_Grid.get(j).add(tokens[i]);
                }
            }
            buffer = br.readLine();
        }
    }

    private static void readPR() throws Exception {

        System.err.println("Reading PRIMARY RESULTS");

        String buffer = skipBlankLines();
        String[] tokens;

        tokens = buffer.split("\\:");
        dh.PR_start = Double.parseDouble(tokens[1]);

        buffer = br.readLine();
        tokens = buffer.split("\\:");
        dh.PR_end = Double.parseDouble(tokens[1]);
    }

    private static void readBCR() throws Exception {

        System.err.println("Reading BEAM CENTERING RESULTS");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.BCR_col_title.add(tokens[i]);
            }
        }

        int row_counter = 0;
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        while (tokens.length != 1) {
            dh.BCR_row_title.add(tokens[0]);
            dh.BCR_Grid.add(new ArrayList<String>());
            for (int i = 1; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.BCR_Grid.get(row_counter).add(tokens[i]);
                }
            }
            buffer = br.readLine();
            tokens = buffer.split(("\\t"));
            row_counter++;
        }
    }

    private static void readRD() throws Exception {

        System.err.println("Reading RAW DATA");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.RD_col_title.add(tokens[i]);
            }
        }

        buffer = skipBlankLines();
        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.RD_col_title.add(tokens[i]);
            }
        }

        int row_counter = 0;
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        while (tokens.length != 1) {
            dh.RD_Grid.add(new ArrayList<String>());
            for (int i = 0; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.RD_Grid.get(row_counter).add(tokens[i]);
                }
            }
            buffer = br.readLine();
            tokens = buffer.split(("\\t"));
            row_counter++;
        }
    }

    private static void readPID() throws Exception {

        System.err.println("Reading PRIMARY INTENSITY DATA");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.PID_col_title.add(tokens[i]);
            }
        }

        buffer = skipBlankLines();
        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.PID_col_title.add(tokens[i]);
            }
        }

        int row_counter = 0;
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        while (tokens.length != 1) {
            dh.PID_Grid.add(new ArrayList<String>());
            for (int i = 0; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.PID_Grid.get(row_counter).add(tokens[i]);
                }
            }
            buffer = br.readLine();
            tokens = buffer.split(("\\t"));
            row_counter++;
        }
    }

    private static void readTD() throws Exception {

        System.err.println("Reading TIMING  DATA ");

        String buffer = skipBlankLines();
        String tokens[];

        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.TD_col_title.add(tokens[i]);
            }
        }

        buffer = skipBlankLines();
        // process column titles
        tokens = buffer.split("\\t");
        for (int i = 0; i < tokens.length; i++) {
            if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                dh.TD_col_title.add(tokens[i]);
            }
        }

        int row_counter = 0;
        buffer = skipBlankLines();
        tokens = buffer.split("\\t");
        while (tokens.length != 1) {
            dh.TD_Grid.add(new ArrayList<String>());
            for (int i = 0; i < tokens.length; i++) {
                if ((new StringTokenizer(tokens[i]).hasMoreElements())) {
                    dh.TD_Grid.get(row_counter).add(tokens[i]);
                }
            }
            buffer = br.readLine();
            tokens = buffer.split(("\\t"));
            row_counter++;
        }
    }

    private static void cleanDataHouse(DataHouse dh)    {
        StringTokenizer st = new StringTokenizer(dh.system);
        dh.system = st.nextToken();

        st = new StringTokenizer(dh.date);
        dh.date = st.nextToken();

        st = new StringTokenizer(dh.SampleName);
        dh.SampleName = "";
        while (st.hasMoreTokens())
            dh.SampleName += st.nextToken();

        st = new StringTokenizer(dh.ACFN);
        dh.ACFN = "";
        while (st.hasMoreTokens())
            dh.ACFN += st.nextToken();

        st = new StringTokenizer(dh.CFN);
        dh.CFN = "";
        while (st.hasMoreTokens())
            dh.CFN += st.nextToken();

        st = new StringTokenizer(dh.FD);
        dh.FD = "";
        while (st.hasMoreTokens())
            dh.FD += st.nextToken();

        cleanGrid(dh.AQP_Grid);
        cleanGrid(dh.DTP_Grid);
        cleanGrid(dh.BCR_Grid);
        cleanGrid(dh.CR_Grid);
        cleanGrid(dh.FR_Grid);
        cleanGrid(dh.PID_Grid);
        cleanGrid(dh.RD_Grid);
        cleanGrid(dh.TD_Grid);
    }

    private static void cleanGrid(ArrayList<ArrayList<String>> grid) {
        StringTokenizer st;

        for (int i = 0; i < grid.size(); i++)  {
            for (int j = 0; j < grid.get(i).size(); j++) {
                String temp = "";
                st = new StringTokenizer(grid.get(i).get(j));
                while (st.hasMoreTokens())
                    temp += st.nextToken();
                grid.get(i).set(j, temp);
            }
        }
    }
}
