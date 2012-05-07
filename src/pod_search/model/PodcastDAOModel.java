package pod_search.model;

import java.sql.*;
import org.apache.log4j.Logger;
import java.io.*;

public class PodcastDAOModel {
	public static final String driver = "org.sqlite.JDBC";
	private Connection con;
  	private Statement stmt;
  	private ResultSet res;
    private String _table;
    private int numberOfResults;

  	private Logger logger = Logger.getLogger(getClass().getName());

    public PodcastDAOModel(String table) {
        _table = table;
    }

  	private void setup() {
    try
    {
        Driver d = (Driver)Class.forName(driver).newInstance();
        DriverManager.registerDriver(d);
    }
    catch(Exception e)
    {
        logger.debug("Error loading database driver: " + e.toString());
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
            logger.debug("Error creating connection: " + e.toString());
            return;
        }
  }

  public PodcastModel[] selectQuery(String filter) {

    try
        {
            setup();
            connect();
            String sql = "SELECT * FROM " + _table + " " + filter;
            stmt = con.createStatement();
            res = stmt.executeQuery("SELECT COUNT(*) FROM " + _table + " " + filter);
            numberOfResults = res.getInt("COUNT(*)");
            res = stmt.executeQuery(sql);
            return readResult();
        }
        catch(SQLException e)
        {
            logger.debug("Error creating or running statement: " + e.toString());
            try
            {
                close();
            }
            catch(Exception ex)
            {
                logger.fatal(ex.toString());
                logger.trace(ex); 
            }
            return null;
        }
  }

   public PodcastModel[] selectAll() {
        return selectQuery("");
   }

  //TODO: return 2D array of results
  public void insertQuery(PodcastModel podcast) {

    try
        {
            setup();
            connect();
            String insertQuery = "INSERT INTO " + _table
                      + " VALUES(NULL,'" + podcast.getName()
                      + "','" + podcast.getLink()
                      + "');";
            stmt = con.createStatement();
            stmt.executeUpdate(insertQuery);
            close();
        }
        catch(SQLException e)
        {
            logger.debug("Error creating or running statement: " + e.toString());
            try
            {
                close();
            }
            catch(Exception ex)
            {
                logger.fatal(ex.toString());
                logger.trace(ex); 
            }
            return;
        }
  }

  private PodcastModel[] readResult() {
    try
        {
            logger.info("Enter readResult(): "+numberOfResults);
            PodcastModel[] podcastModels = new PodcastModel[numberOfResults];
            for (int i = 0; i < numberOfResults; i = i + 1)
            {
                if (!res.next()) break;
                logger.info("readResult() in loop: "+i);
                PodcastModel podcastModel = new PodcastModel();
                podcastModel.setName(res.getString("name"));
                podcastModel.setID(res.getInt("podID"));
                podcastModel.setLink(res.getString("link"));
                podcastModels[i] = podcastModel;
            }
            close();
            return podcastModels;
        }
        catch(Exception e)
        {
            System.out.println("Error processing results: " + e.toString());
            try
            {
            	close();
            }
            catch(Exception ex)
            {
                logger.fatal(ex.toString());
                logger.trace(ex); 
            }
            return null;
        }
  }

  private void close() {
    try
        {
          // System.out.println("close");
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        catch(SQLException e)
        {
            logger.debug("Error closing connections: " + e.toString());
            return;
        }
  }
}