/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenericSpreadsheetWindow.java
 *
 * Created on 17/09/2010, 8:46:18 PM
 */

package data_summary;

import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 *
 * @author ragil
 */
public class GenericSSWindow extends javax.swing.JFrame {

    private DefaultTableModel dtm;
    private int[] defaultSize;
    private boolean enableCalc;

    /** Creates new form GenericSpreadsheetWindow */
    public GenericSSWindow(DefaultTableModel dtm, String title, boolean calculate, int[] defaultSize) {
        this.dtm = dtm;
        initComponents();
        this.setTitle(title);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.defaultSize = defaultSize;

        setComboBox();
        resetSize();

        enableCalc = calculate;
        
        if (!calculate) {
            pluginButton.setEnabled(false);
            pluginSelection.setEnabled(false);
        }
        pluginButton.setVisible(false);
        pluginSelection.setVisible(false);
    }

    /** Creates new form GenericSpreadsheetWindow */
    public GenericSSWindow(DefaultTableModel dtm, String title, boolean calculate) {
        this(dtm, title, calculate, new int[0]);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        graphButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        yMeanSelection = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        yErrSelection = new javax.swing.JComboBox();
        yBox = new javax.swing.JCheckBox();
        xBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        xMeanSelection = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        xErrSelection = new javax.swing.JComboBox();
        pluginButton = new javax.swing.JButton();
        pluginSelection = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(dtm);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        graphButton.setText("Graph");
        graphButton.setName("graphButton"); // NOI18N
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Mean");
        jLabel1.setName("jLabel1"); // NOI18N

        yMeanSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yMeanSelection.setName("yMeanSelection"); // NOI18N

        jLabel2.setText("Error");
        jLabel2.setName("jLabel2"); // NOI18N

        yErrSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yErrSelection.setName("yErrSelection"); // NOI18N

        yBox.setSelected(true);
        yBox.setText("Use Y");
        yBox.setName("yBox"); // NOI18N

        xBox.setSelected(true);
        xBox.setText("Use X");
        xBox.setName("xBox"); // NOI18N

        jLabel3.setText("Mean");
        jLabel3.setName("jLabel3"); // NOI18N

        xMeanSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        xMeanSelection.setName("xMeanSelection"); // NOI18N

        jLabel4.setText("Error");
        jLabel4.setName("jLabel4"); // NOI18N

        xErrSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        xErrSelection.setName("xErrSelection"); // NOI18N

        pluginButton.setText("Calculate");
        pluginButton.setName("pluginButton"); // NOI18N
        pluginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pluginButtonActionPerformed(evt);
            }
        });

        pluginSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pluginSelection.setName("pluginSelection"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pluginSelection, 0, 156, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pluginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xMeanSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(xErrSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(yBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yMeanSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(yErrSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(graphButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(graphButton)
                        .addComponent(yErrSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(yMeanSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(yBox))
                    .addComponent(xErrSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(xMeanSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(xBox)
                        .addComponent(pluginButton)
                        .addComponent(pluginSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        plotGraph();
    }//GEN-LAST:event_graphButtonActionPerformed

    private void pluginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pluginButtonActionPerformed
        DSFunctions.calcUsingFormula(null, getSelectedRow(2), getSelectedRow(1));
    }//GEN-LAST:event_pluginButtonActionPerformed

    public void setEnabled(boolean value)    {
        if (enableCalc)
            pluginButton.setEnabled(value);
        graphButton.setEnabled(value);
    }

    private void plotGraph()    {

        if (!xBox.isSelected())    {
            System.err.println("select x-axis");
            javax.swing.JOptionPane.showMessageDialog(null, "Select x-axis.", "No selection",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!yBox.isSelected())    {
            System.err.println("select y-axis");
            javax.swing.JOptionPane.showMessageDialog(null, "Select y-axis.", "No selection",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yMeanSelection.getSelectedIndex() == 0 && xBox.isSelected() ||
                xMeanSelection.getSelectedIndex() == 0 && yBox.isSelected())   {
            javax.swing.JOptionPane.showMessageDialog(null, "Select appropriate columns.", "No selection",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int[] selectedRows = getSelectedRow(1);
        int[] stdRows = getSelectedRow(2);

        if (selectedRows.length == 0 && stdRows.length == 0)   {
            javax.swing.JOptionPane.showMessageDialog(null, "You must select atleast 1 input file to plot.\n" +
                    "Select by checking the checkboxes\n",
                    "No files selected", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] column = new String[dtm.getColumnCount()];
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(nullValue,0);
        for (int i = 0; i < column.length; i++)
            map.put(dtm.getColumnName(i), i+1);

        int ymean = map.get(yMeanSelection.getSelectedItem().toString());
        int yerror = map.get(yErrSelection.getSelectedItem().toString());
        int xmean = map.get(xMeanSelection.getSelectedItem().toString());
        int xerror = map.get(xErrSelection.getSelectedItem().toString());

        String[] legend = {"STD", "USE"};
        // 0 std - 1 use
        double[][][] data = new double[2][6][];
        for (int i = 0; i < 6; i++) {
            data[0][i] = new double[selectedRows.length];
            data[1][i] = new double[stdRows.length];
        }

        for (int i = 0; i < selectedRows.length + stdRows.length; i++)   {
            // x min max y min max
            double xmid;
            double xerr;
            double ymid;
            double yerr;

            int index = -1;
            if (i < selectedRows.length)
                index = selectedRows[i];
            else
                index = stdRows[i - selectedRows.length];
            
            try {
                if (xmean != nullIndex && xBox.isSelected())  {
                     xmid = Double.parseDouble(dtm.getValueAt(index, xmean-1).toString());
                     if (xerror != nullIndex)   {
                         xerr = Double.parseDouble(dtm.getValueAt(index, xerror-1).toString());
                         xerr = xerr / 100 * xmid;
                     } else {
                         xerr = 0;
                     }
                } else  {
                    xmid = i;
                    xerr = 0;
                }

                if (ymean != nullIndex && yBox.isSelected())  {
                     ymid = Double.parseDouble(dtm.getValueAt(index, ymean-1).toString());
                     if (yerror != nullIndex)   {
                         yerr = Double.parseDouble(dtm.getValueAt(index, yerror-1).toString());
                         yerr = yerr / 100 * ymid;
                     } else {
                         yerr = 0;
                     }
                } else  {
                    ymid = i;
                    yerr = 0;
                }

            } catch (Exception e)   {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, "Cannot generate data for plot.\nCheck input selection.", "Wrong Selection", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            int temp = i < selectedRows.length ? 0 : 1;
            int temp2 = i < selectedRows.length ? i : i - selectedRows.length;

            data[temp][0][temp2] = xmid;
            data[temp][1][temp2] = xmid - xerr;
            data[temp][2][temp2] = xmid + xerr;
            data[temp][3][temp2] = ymid;
            data[temp][4][temp2] = ymid - yerr;
            data[temp][5][temp2] = ymid + yerr;
        }

        graph.jfreecharts.GraphFrontEnd.plotData(null, xMeanSelection.getSelectedItem().toString(),
                yMeanSelection.getSelectedItem().toString(), legend, data);
    }

    private int[] getSelectedRow(int colNum)   {
        int totalSelected = 0;
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++)
            if ((Boolean)dtm.getValueAt(i, colNum))
                totalSelected ++;

        int[] result = new int[totalSelected];
        int counter = 0;
        for (int i = 0; counter != result.length && i < rowCount; i++)
            if ((Boolean)dtm.getValueAt(i, colNum)) {
                result[counter++] = i;
            }
        return result;
    }

    public void setComboBox()  {
        int numCol = dtm.getColumnCount();

        if (numCol == 0)
            return;
        
        String[] colTitle = new String[numCol];
        for (int i = 0; i < numCol; i++)    {
            colTitle[i] = dtm.getColumnName(i);
        }

        yMeanSelection.removeAllItems();
        yErrSelection.removeAllItems();
        xMeanSelection.removeAllItems();
        xErrSelection.removeAllItems();

        yErrSelection.addItem(nullValue);
        yMeanSelection.addItem(nullValue);
        xErrSelection.addItem(nullValue);
        xMeanSelection.addItem(nullValue);

        for (int i = 0; i < numCol; i++)    {

            if (!checkColumn(i))
                continue;

            yErrSelection.addItem(colTitle[i]);
            yMeanSelection.addItem(colTitle[i]);
            xErrSelection.addItem(colTitle[i]);
            xMeanSelection.addItem(colTitle[i]);
        }
    }

    private boolean checkColumn(int col)   {
        int total = dtm.getRowCount();
        int counter = 0;
        for (int i = 0; i < total; i++) {
            try {
                new Double(dtm.getValueAt(i, col).toString());
                counter++;
            } catch (Exception e)   {
                
            }
        }

        return counter >= total /2;
    }

    public void resetSize()    {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        TableColumn column = null;
        int numCol = dtm.getColumnCount();
        for (int i = 0; i < numCol; i++) {
            column = jTable1.getColumnModel().getColumn(i);
            column.setPreferredWidth(i >= defaultSize.length ? 125 : defaultSize[i]);
            //jTable1.setDefaultRenderer(jTable1.getColumnClass(i), dtcr);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pluginButton;
    private javax.swing.JComboBox pluginSelection;
    private javax.swing.JCheckBox xBox;
    private javax.swing.JComboBox xErrSelection;
    private javax.swing.JComboBox xMeanSelection;
    private javax.swing.JCheckBox yBox;
    private javax.swing.JComboBox yErrSelection;
    private javax.swing.JComboBox yMeanSelection;
    // End of variables declaration//GEN-END:variables

    private static final String nullValue = "NULL Field";
    private static final int nullIndex = 0;
}
