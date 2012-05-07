// View

package pod_search.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import pod_search.model.*;

public class PodSearchView extends JFrame {
    
    //... Components
    private JTextField  tfSearchQuery   = new JTextField(40);
    private JTextField  tfSelectResult  = new JTextField(40);
    private JTextArea   taResultsPanel  = new JTextArea(25,60);
    private JButton     btnSearch       = new JButton("Search");
    private JButton     btnView         = new JButton("View Saved Pod Casts");
    private JButton     btnSave         = new JButton("Save");
    private JComboBox   cbxListResults  = new JComboBox();
    
    private PodcastStorageModel m_model;
    private PodcastModel[] results;
    
    //======================================================= constructor
    /** Constructor */
    public PodSearchView(PodcastStorageModel model) {
        //... Set up the logic
        m_model = model;
        //m_model.setValue(INITIAL_VALUE);
        
        //... Initialize components
        
        //... Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;

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
        // content.add(tfSelectResult, gBC);
        cbxListResults.setPreferredSize(new Dimension(60,25));
        //cbxListResults.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        content.add(cbxListResults, gBC);
        gBC.gridx = 2;
        gBC.gridy = 1;
        toggleSaveButton(false);
        content.add(btnSave, gBC);
        gBC.gridx = 0;
        gBC.gridy = 2;
        gBC.gridwidth = 3;
        content.add(new JLabel("Results"), gBC);
        gBC.gridx = 0;
        gBC.gridy = 3;
        gBC.gridwidth = 3;
        gBC.weighty = 1;
        gBC.weightx = 1;
        //gBC.gridheight = GridBagConstraints.REMAINDER;
        JScrollPane scrlResultsPanel = new JScrollPane(taResultsPanel);
        content.add(scrlResultsPanel, gBC);
        gBC.gridx = 0;
        gBC.gridy = 4;
        gBC.weighty = 0;
        gBC.weightx = 0;
        content.add(btnView, gBC);
        
        //... finalize layout
        this.setContentPane(content);
        this.pack();
        
        this.setTitle("PodSearch");
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void toggleSaveButton(boolean value) {
        btnSave.setEnabled(value);
    }

    public PodcastModel[] getResults() {
        return results;
    }
    
    public void reset() {
       // m_totalTf.setText(INITIAL_VALUE);
    }
    
    public String getSearchQuery() {
        return tfSearchQuery.getText();
    }
    
    public void setResultOutput(PodcastModel[] podcastModels) {
        results = podcastModels;
        String output = "";

        cbxListResults.removeAllItems();

        for (int i = 0; i< results.length; i++) {
            output += results[i].getID() + " " + 
                    results[i].getName() + " " + 
                    results[i].getLink() + "\n";
            cbxListResults.addItem(new PodcastModel(results[i]));
        }

        taResultsPanel.setText(output);
    }
    
    public void setResultOutput(String output) {
        taResultsPanel.setText(output);
    }

    public PodcastModel getSelectedPodcast() {
        return (PodcastModel)cbxListResults.getSelectedItem();
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