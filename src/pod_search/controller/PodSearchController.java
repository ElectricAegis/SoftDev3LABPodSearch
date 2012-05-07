// stucture/calc-mvc/CalcController.java - Controller
//    Handles user interaction with listeners.
//    Calls View and Model as needed.
// Fred Swartz -- December 2004

package pod_search.controller;

import java.awt.event.*;
import pod_search.view.*;
import pod_search.model.*;
import java.util.logging.Logger;

public class PodSearchController {
    //... The Controller needs to interact with both the Model and View.
    private PodcastStorageModel m_model;
    private DigitalPodModel digitalPodModel;
    private PodSearchView  m_view;
    private Logger logger = Logger.getLogger(getClass().getName());

    
    //========================================================== constructor
    /** Constructor */
    public PodSearchController(PodcastStorageModel model, PodSearchView view) {
        m_model = model;
        m_view  = view;
        
        //... Add listeners to the view.
        view.addSearchListener(new SearchListener());
        view.addSaveListener(new SaveListener());
        view.addViewResultListener(new ViewResultListener());
    }
    
    
    ////////////////////////////////////////// inner class MultiplyListener
    /** When a mulitplication is requested.
     *  1. Get the user input number from the View.
     *  2. Call the model to mulitply by this number.
     *  3. Get the result from the Model.
     *  4. Tell the View to display the result.
     * If there was an error, tell the View to display it.
     */
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchQuery = "";
            try {
                searchQuery = m_view.getSearchQuery();
                digitalPodModel = new DigitalPodModel();
                digitalPodModel.search(searchQuery);
                m_view.setResultOutput(digitalPodModel.getSearchResult1());
                m_view.toggleSaveButton(true);
                
            } catch (NumberFormatException nfex) {
                m_view.showError("Bad input: '" + searchQuery + "'");
            }
        }
    }//end inner class MultiplyListener
    
    
    //////////////////////////////////////////// inner class ClearListener
    /**  1. Reset model.
     *   2. Reset View.
     */    
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //m_model.reset();
            //m_view.reset();
            PodcastModel selectedCast = m_view.getSelectedPodcast();
            m_view.showError("Saved Podcast");
            // m_view.setResultOutput(results[0].toString());
            try {
                m_model.savePodcast(selectedCast);
            } catch(Exception ex) {
                // Logger.info(ex.toString());
            }
        }
    }// end inner class ClearListener

    class ViewResultListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //m_model.reset();
            //m_view.reset();
            try {
                PodcastModel temp[] = m_model.getSavedPodcasts();
                m_view.setResultOutput(temp);
                m_view.toggleSaveButton(false);
            } catch (Exception ex) {
                m_view.showError("Bad input: '" + ex.toString() + "'");
            }
        }
    }// end inner class ClearListener
}