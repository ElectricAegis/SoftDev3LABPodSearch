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

  private void setup() {
    try
    {
      System.out.println("setup");
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
          System.out.println("connect");
            //FIXME: Load database filename via external config.
            String url = "jdbc:sqlite:podcastdata.sqlite";
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
          System.out.println("query");
            stmt = con.createStatement();
            res = stmt.executeQuery("SELECT COUNT(*) FROM podcasts");
            numberOfResults = res.getInt();
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

  private void readResult() {
    try
        {
            //while(res.next())
            System.out.println("readResult 1");
            res.last();
            System.out.println("eish");
            podcastModels = new PodcastModel[res.getRow()];
            System.out.println("readResult 1.5");
            res.beforeFirst();
            System.out.println("readResult 2");
            for (int i = 1; i < 100; i = i + 1)
            {
              System.out.println("readResult 3");
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
                res.close();
                stmt.close();
                con.close();
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
          System.out.println("close");
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