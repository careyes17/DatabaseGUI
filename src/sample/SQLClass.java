package sample;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// CheckStyle warning - Abbreviation intentional
public class SQLClass {

  //URL for the database used in this program. This link contains the database extension.
  private static String url = "jdbc:mysql://den1.mysql6.gear.host/integerdatabase";
  //This is the username that my database utilizes.
  private static String user = "integerdatabase";
  //This is the password that my database needs to be accessed. This is input by the user.
  private static String password = "";

  /**
   * Connects to the remote mysql database and prints "Connected" to the console once a connection
   * has been established.
   */
  public static Connection getConnection() throws Exception {

    try {
      //Driver necessary for establishing a connection to a MySQL database using Java.
      String driver = "com.mysql.cj.jdbc.Driver";
      Class.forName(driver);

      /*
       * To connect to a database, one must have a URL to point to, a username, and a password in
       * order to be granted access privileges. Utilizing the DriverManager, we are able to create
       * a static variable that grants methods permission to operate with the given database.
       */
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
      // Establishes a connection through a variable
      Connection con = getConnection();
      // Ignore FindBugs error - produced on Java 1.8
      // Prepares an SQL statement to add a record with a variable integer
      PreparedStatement statement = con.prepareStatement("SELECT * FROM cars");

      // Ignore FindBugs error - produced on Java 1.8
      ResultSet result = statement.executeQuery();

      ArrayList<String> array = new ArrayList<String>();
      while (result.next()) {
        //System.out.println(result.getString("Cars") + '\n');
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

  /**
   * Adds a record to the database, taking a user given integer input.
   */
  public static void addRecord(int inputInteger) throws Exception {
    try {
      // Establishes a connection through a variable
      Connection con = getConnection();
      // Ignore FindBugs error - produced on Java 1.8
      // Prepares an SQL statement to add a record with a variable integer
      PreparedStatement statement = con
          .prepareStatement("INSERT INTO Cars (cars)VALUES (" + inputInteger + ")");

      // Executes the statement "statement", sending this information to the remote database
      statement.executeUpdate();

      // Closes unnecessarily open connection elements
      System.out.println("Integer Recorded");
      statement.close();
      con.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Updates the last value of the database with a user given input according to a key value index.
   */
  public static void updateRecord(int inputInteger) throws Exception {
    try {
      // Establishes a connection through a variable
      Connection con = getConnection();
      // Ignore FindBugs error - produced on Java 1.8
      // Prepares an SQL statement to add a record with a variable integer
      PreparedStatement statement = con.prepareStatement(
          "UPDATE cars SET Cars='" + inputInteger + "' WHERE cars_pk='" + get().size() + "';");

      statement.executeUpdate();

      System.out.println("Integer Recorded");
      statement.close();
      con.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void setPassword(String password) {
    SQLClass.password = password;
  }

}
