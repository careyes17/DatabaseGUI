package sample;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// CheckStyle warning - Abbreviation intentional
public class SQLClass {

  private static String url = "jdbc:mysql://den1.mysql6.gear.host/integerdatabase";
  private static String user = "integerdatabase";
  private static String password = "";

  /**
   * Connects to the remote mysql database and prints "Connected" to the console once a connection
   * has been established.
   */
  public static Connection getConnection() throws Exception {

    try {
      String driver = "com.mysql.cj.jdbc.Driver";
      Class.forName(driver);

      Connection connection = DriverManager.getConnection(url, user, password);
      System.out.println("Connected");
      return connection;
      //  return connection;
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

  /**
   * Gets the single integer value stored in the database.
   */
  public static ArrayList<String> get() throws Exception {
    try {
      Connection con = getConnection();
      // Ignore FindBugs error - produced on Java 1.8
      PreparedStatement statement = con.prepareStatement("SELECT * FROM cars");

      // Ignore FindBugs error - produced on Java 1.8
      ResultSet result = statement.executeQuery();

      ArrayList<String> array = new ArrayList<String>();
      while (result.next()) {
        System.out.println(result.getString("Cars") + '\n');
        array.add(result.getString("Cars"));

      }
      System.out.println("All records have been selected!");
      statement.close();
      result.close();
      con.close();
      return array;

    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  public static void setPassword(String password) {
    SQLClass.password = password;
  }

}
