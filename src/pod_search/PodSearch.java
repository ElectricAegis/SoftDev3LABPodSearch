//Main file

package pod_search;

import javax.swing.*;

import pod_search.model.*;
import pod_search.view.*;
import pod_search.controller.*;

public class PodSearch {
    //... Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
    public static void main(String[] args) {
        
        PodcastStorageModel model      = new PodcastStorageModel();
        PodSearchView       view       = new PodSearchView(model);
        PodSearchController controller = new PodSearchController(model, view);
        
        view.setVisible(true);
    }
}