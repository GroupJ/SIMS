package plugin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 20378332
 */
public interface Formula_Iterative {

    public void processStandard(StandardData std);

    public void processUse(UseRowContent use);

    public String[] getColumnTitle();

    public Object[] getResult();
}
