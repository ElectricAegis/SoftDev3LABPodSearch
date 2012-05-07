package pod_search.model;

import org.apache.log4j.Logger;

public class PodcastStorageModel {

  private PodcastModel[] podcastModels;
  private Logger logger = Logger.getLogger(getClass().getName());
  private PodcastDAOModel podcastDAOModel  = new PodcastDAOModel("podcasts");

  public PodcastModel[] getSavedPodcasts(){
      podcastModels = getDAO().selectAll();
      return podcastModels;
  }

  public void savePodcast(PodcastModel podcast) throws NullPointerException{
    if (podcast == null) throw new NullPointerException("PodcastModel is cannot be null in savePodcast");
    try {
      getDAO().insertQuery(podcast);
    }catch (Exception ex) {
      logger.fatal(ex.toString());
    }
  }

  public void saveAllPodcasts() {
    for (int i=0; i<podcastModels.length; i++) {
      savePodcast(podcastModels[i]);
    }
  }

  public PodcastDAOModel getDAO(){
    return podcastDAOModel;
  }

}