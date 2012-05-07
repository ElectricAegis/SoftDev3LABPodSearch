package pod_search.model;

import java.sql.*;
import java.io.*;
// import java.util.logging.Logger;
import org.apache.log4j.Logger;

public class PodcastStorageModel {

  private PodcastModel[] podcastModels;
  private Logger logger = Logger.getLogger(getClass().getName());
  private PodcastDAOModel podcastDAOModel = new PodcastDAOModel("podcasts");

  public PodcastModel[] getSavedPodcasts(){
      podcastModels = podcastDAOModel.selectAll();
      return podcastModels;
  }

  public void savePodcast(PodcastModel podcast) throws NullPointerException{
    try {
      if (podcast == null) throw new NullPointerException();
      podcastDAOModel.insertQuery(podcast);
    }catch (Exception ex) {
      // logger.info(ex.toString());
      logger.fatal(ex.toString());
    }
  }

  public void saveAllPodcasts() {
    for (int i=0; i<podcastModels.length; i++) {
      savePodcast(podcastModels[i]);
    }
  }

}