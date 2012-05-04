// View

package pod_search.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import pod_search.model.*;

public class PodSearchView extends JFrame {
    
    //... Components
    private JTextField  tfSearchQuery   = new JTextField(20);
    private JTextField  tfSelectResult  = new JTextField(20);
    private JTextArea   taResultsPanel  = new JTextArea(10,20);
    private JButton     btnSearch       = new JButton("Search");
    private JButton     btnView         = new JButton("View Saved Pod Casts");
    private JButton     btnSave         = new JButton("Save");
    
    private PodResultsModel m_model;
    
    //======================================================= constructor
    /** Constructor */
    public PodSearchView(PodResultsModel model) {
        //... Set up the logic
        m_model = model;
        //m_model.setValue(INITIAL_VALUE);
        
        //... Initialize components
        //m_totalTf.setText(m_model.getValue());
        //m_totalTf.setEditable(false);
        
        //... Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.HORIZONTAL;

        gBC.gridx = 0;
        gBC.gridy = 0;
        content.add(new JLabel("Search"), gBC);
        gBC.gridx = 1;
        gBC.gridy = 0;
        content.add(tfSearchQuery, gBC);
        gBC.gridx = 2;
        gBC.gridy = 0;
        content.add(btnSearch, gBC);
        gBC.gridx = 0;
        gBC.gridy = 1;
        content.add(new JLabel("Select Result"), gBC);
        gBC.gridx = 1;
        gBC.gridy = 1;
        content.add(tfSelectResult, gBC);
        gBC.gridx = 2;
        gBC.gridy = 1;
        content.add(btnSave, gBC);
        gBC.gridx = 0;
        gBC.gridy = 2;
        gBC.gridwidth = 3;
        content.add(new JLabel("Results"), gBC);
        gBC.gridx = 0;
        gBC.gridy = 3;
        gBC.gridwidth = 3;
        //gBC.gridheight = GridBagConstraints.REMAINDER;
        content.add(taResultsPanel, gBC);
        gBC.gridx = 0;
        gBC.gridy = 4;
        content.add(btnView, gBC);
        
        //... finalize layout
        this.setContentPane(content);
        this.pack();
        
        this.setTitle("PodSearch");
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void reset() {
       // m_totalTf.setText(INITIAL_VALUE);
    }
    
    public String getSearchQuery() {
        return tfSearchQuery.getText();
    }
    
    public void setResultOutput(String newTotal) {
        taResultsPanel.setText(newTotal);
    }
    
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    
    public void addSearchListener(ActionListener search) {
        btnSearch.addActionListener(search);
    }
    
    public void addSaveListener(ActionListener save) {
        btnSave.addActionListener(save);
    }

    public void addViewResultListener(ActionListener view) {
        btnView.addActionListener(view);
    }
}