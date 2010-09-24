/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plugin;

/**
 *
 * @author 20378332
 */
public interface Formula {

    public void processUserInput(UserInputData uid);

    public void processStandard(StandardData std);

    public void processUse(UseContent use);

    public String[] getColumnTitle();

    public Object[][] getResult();
    
}
