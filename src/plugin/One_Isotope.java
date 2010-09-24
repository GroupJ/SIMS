/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author 20378332
 */
public class One_Isotope implements Formula_Iterative   {

    private int numCol = 4;
    Object[] result = new Object[numCol];
    private UserInputData uid;
    private StandardData std;

    private double sigma = 0;
    private double sigmase = 0;
    
    public void processUserInput(UserInputData arg0) {
        uid = arg0;
    }

    public void processStandard(StandardData arg0)   {
        std = arg0;

        String[] standard_r0 = std.getAllStd("R0 (MV)");
        String[] standard_r0se = std.getAllStd("R0 (Std Err(%))");

        double standard_value = new Double(uid.getContentByName("Mon", "Std Deviation"));
        double unc_stdVal = new Double(uid.getContentByName("Mon", "Values"));

        double total_weight = 0;
        double total_weight_alpha = 0;

        int length = standard_r0.length;
        for (int i = 0; i < length; i++)    {

            // convert to double types
            double r0 = new Double(standard_r0[i]);
            double r0se = new Double(standard_r0se[i]);

            double delraw = ((r0 / standard_value) - 1) * 1000;
            double delrawse = 100 / standard_value * r0se;

            double alpha = (1 + (delraw / 1000)) / (1 + (standard_value / 1000));
            double alpha_err = Math.sqrt((Math.pow(((double)i + 1000 + delraw) * delrawse,2) +
                    ( -1 * (( 1 + (delraw / 1000)) / (1000 + standard_value)) * Math.pow(unc_stdVal, 2))));

            double weight = 1 / Math.pow(alpha_err,2);
            double weight_alpha = weight * alpha;

            total_weight += weight;
            total_weight_alpha += weight_alpha;

            System.err.printf("ro %f rose %f delraw %f delrawse %f alpha %f alpha_err %f weight %f weight_alpha %f total %f totalpha %f",
                    r0, r0se, delraw, delrawse, alpha, alpha_err, weight, weight_alpha, total_weight, total_weight_alpha);
        }

        sigma = total_weight_alpha / total_weight;
        sigmase = Math.sqrt(1 / total_weight);
    }

    public void processUse(UseRowContent use)   {
        result[0] = use.getContentByTitle("File #");

        double r0 = new Double(use.getContentByTitle("R0 (MV)"));
        double r0se = new Double(use.getContentByTitle("R0 (Std Err(%))"));
        double standard_value = new Double(uid.getContentByName("Mon", "Std Deviation"));

        double delraw = ((r0 / standard_value) - 1) * 1000;
        double delrawse = 100 / standard_value * r0se;

        System.err.printf("delraw %f delrawse %f sigma %f ", delraw, delrawse, sigma);

        double del = ((1 + delraw / 1000) / sigma - 1) * 1000;
        double delse = Math.sqrt((Math.pow((1 / sigma * delrawse), 2)) +
                Math.pow(((-1000 / (sigma * sigma) - del / (sigma * sigma)) * sigmase), 2));
        double delse_2 = delse * 2;

        System.err.printf("del %f delse %f\n", del, delse);

        result[1] = new Double(del);
        result[2] = new Double(delse);
        result[3] = new Double(delse_2);
    }

    public String[] getColumnTitle()    {
        String[] column_title = new String[numCol];

        column_title[0] = "File Number";
        column_title[1] = "R0 Del";
        column_title[2] = "R0 DelSE";
        column_title[3] = "R0 DelSE * 2";

        return column_title;
    }

    public Object[] getResult() {
        return result;
    }
}
