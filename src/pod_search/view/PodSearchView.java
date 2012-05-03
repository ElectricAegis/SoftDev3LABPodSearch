// View

package pod_search.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import pod_search.model.*;

public class PodSearchView extends JFrame {
    
    //... Components
    private JTextField  tfSearchQuery   = new JTextField(20);
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
        //FlowLayout 
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Search"));
        content.add(tfSearchQuery);
        content.add(btnSearch);
        content.add(new JLabel("Results"));
        content.add(taResultsPanel);
        content.add(btnSave);
        content.add(btnView);
        
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