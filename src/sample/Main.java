package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage primaryStage;

  /**
   * Loads the Tree Table GUI in "treetable.fxml"
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    setPrimaryStage(primaryStage);
    Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
    primaryStage.setTitle("DatabaseGUI");
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void setPrimaryStage(Stage stage) {
    Main.primaryStage = stage;
  }

  static Stage getPrimaryStage() {
    return primaryStage;
  }

}
