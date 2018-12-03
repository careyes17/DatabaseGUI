package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  /**
   * Loads the Tree Table GUI in "treetable.fxml"
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("treetable.fxml"));
    primaryStage.setTitle("DatabaseGUI");
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

  public static void main(String[] args) {
    // Catches Exceptions caused by the SQLClass.get();
    try {
      Controller.tempvalue = Integer.parseInt(String.valueOf(SQLClass.get().toString().charAt(1)));
    } catch (Exception e) {
      System.out.println(e);
    }

    launch(args);
  }
}
