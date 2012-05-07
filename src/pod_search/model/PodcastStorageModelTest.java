package pod_search.model;

import java.sql.*;
public class PodcastStorageModelTest extends PodcastStorageModel {

  public PodcastDAOModel podcastDAOModelTest = new PodcastDAOModelTest();

  public PodcastDAOModel getDAO(){
    return podcastDAOModelTest;
  }

}