package pod_search.model;

import java.sql.*;
import java.io.*;

public class PodcastStorageModel {

  public static final String driver = "org.sqlite.JDBC";
  private Connection con;
  private Statement stmt;
  private ResultSet res;
  private PodcastModel[] podcastModels;
  private int numberOfResults;

  public PodcastModel[] getSavedPodcasts(){
      setup();
      connect();
      query("SELECT * FROM podcasts");
      readResult();
      close();
      return podcastModels;
  }

  public void savePodcast(PodcastModel podcast) {

    setup();
    connect();
    String insertQuery = "INSERT INTO podcasts "
                      + "VALUES(NULL,'" + podcast.getName()
                      + "','" + podcast.getLink()
                      + "');";
    addQuery(insertQuery);
    // readResult();
    close();
  }

  private void setup() {
    try
    {
      // System.out.println("setup");
        //Load the JDBC driver class dynamically.
        Driver d = (Driver)Class.forName(driver).newInstance();
        DriverManager.registerDriver(d);
    }
    catch(Exception e)
    {
        System.out.println("Error loading database driver: " + e.toString());
        return;
    }
  }

  private void connect() {
    try
        {
          // System.out.println("connect");
            //FIXME: Load database filename via external config.
            String url = "jdbc:sqlite:data/podcastdata.sqlite";
            con = DriverManager.getConnection(url);
        }
        catch(SQLException e)
        {
            System.out.println("Error creating connection: " + e.toString());
            return;
        }
  }

  private void query(String sql) {

    try
        {
          //System.out.println("query");
            stmt = con.createStatement();
            res = stmt.executeQuery("SELECT COUNT(*) FROM podcasts");
            numberOfResults = res.getInt("COUNT(*)");
            res = stmt.executeQuery(sql);
        }
        catch(SQLException e)
        {
            System.out.println("Error creating or running statement: " + e.toString());
            try
            {
                con.close();
            }
            catch(Exception ex)
            {
            }
            return;
        }
  }

  private void addQuery(String sql) {

    try
        {
          //System.out.println("query");
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            System.out.println("Error creating or running statement: " + e.toString());
            try
            {
                con.close();
            }
            catch(Exception ex)
            {
            }
            return;
        }
  }

  private void readResult() {
    try
        {
          // System.out.println("readResult");
            //while(res.next())
            podcastModels = new PodcastModel[numberOfResults];
            for (int i = 0; i < numberOfResults; i = i + 1)
            {
                if (!res.next()) return;
                PodcastModel podcastModel = new PodcastModel();
                podcastModel.setName(res.getString("name"));
                podcastModel.setID(res.getInt("podID"));
                podcastModel.setLink(res.getString("link"));
                podcastModels[i] = podcastModel;
                //System.out.println(fName + " " + lName + "  |  " + email);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error processing results: " + e.toString());
            try
            {
                if (res != null) res.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            }
            catch(Exception ex)
            {
            }
            return;
        }
  }

  private void close() {
    try
        {
          // System.out.println("close");
            res.close();
            stmt.close();
            con.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error closing connections: " + e.toString());
            return;
        }
  }
}