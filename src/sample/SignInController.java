package sample;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SignInController {

  @FXML
  Label errorlabel;

  @FXML
  JFXPasswordField passfield;

  @FXML
  private void updatePassword(ActionEvent event) throws Exception {
    /* Gets text from the password field and assigns it to the password
     * field of the integer database
     */
    SQLClass.setPassword(passfield.getText());

    // If the database does not permit connection
    if (SQLClass.getConnection() == null) {
      // Display error text
      errorlabel.setVisible(true);
    } else {
      // Catches Exceptions caused by the SQLClass.get();
      // Loads the single integer value into "tempvalue"
      try {
        TreeTableController.tempvalue = Integer
            .parseInt(String.valueOf(SQLClass.get().toString().charAt(1)));
      } catch (Exception e) {
        System.out.println(e);
      }
      // Loads the table view
      Stage stage = Main.getPrimaryStage();
      Parent root = FXMLLoader.load(getClass().getResource("treetable.fxml"));
      stage.setScene(new Scene(root, 600, 400));
      stage.show();
    }
  }

}
