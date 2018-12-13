package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

/*
 * This program adheres to GUI design principles in various ways. The program uses a simple sign-in
 * interface that is then met with a UI that has descriptive buttons so the user knows what each
 * action does. If the user does not use a certain button properly, error text will appear
 * explaining what the user must do in order to accomplish their desired operation. This program
 * uses a dark theme with high contrast text in order to allow users to comfortably view the table
 * presented without straining their eyes. Mistakes are not punished in this program, as whenever a
 * user inputs a command, whatever progress they make is always stored in the remote database upon
 * successfully connecting.
 */

public class TreeTableController {

  @FXML
  JFXTreeTableView table;

  @FXML
  JFXTextArea updateField;

  @FXML
  Label errorMessage;

  // Temporary ObservableList that is appended to the default values preloaded
  ObservableList<Schedule> temporary = FXCollections.observableArrayList();

  /* Shared variable that makes "appendRow" append a consecutive integer to make the
   * functionality more apparent
   */
  //static int tempvalue;

  /**
   * Updates the last row of the database table with the integer found in the "updateField" text
   * area.
   */
  @FXML
  private void updateLastRow(ActionEvent event) throws Exception {
    //will store the integer value stored in updateField
    int tempvalue = 0;
    //boolean value used to prevent the ActionEvent from continuing integer isn't given
    boolean passCondition = false;
    //tries to get an integer value from the "updateField" text field
    try {
      tempvalue = Integer.parseInt(updateField.getText());
      //enabling "passCondition" allows database functionality to proceed
      passCondition = true;
    } catch (Exception e) {
      System.out.println("Integer was not entered.");
      errorMessage.setVisible(true);
    }
    if (passCondition) {
      errorMessage.setVisible(false);
      try {
        //Updates the last table row, taking "tempvalue" as the replacement integer
        SQLClass.updateRecord(tempvalue);
      } catch (Exception e) {
        System.out.println("Could not update the table.");
      }
      //Automatically synchronizes database without having to click "Load Database"
      try {
        updateAssignments(new ActionEvent());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Takes an integer value from a remote database and sets that as a default value for the
   * appending rows, then appends them.
   */
  @FXML
  private void appendRow(ActionEvent event) throws Exception {
    //will store the integer value stored in updateField
    int tempvalue = 0;
    //boolean value used to prevent the ActionEvent from continuing integer isn't given
    boolean passCondition = false;
    //tries to get an integer value from the "updateField" text field
    try {
      tempvalue = Integer.parseInt(updateField.getText());
      //enabling "passCondition" allows database functionality to proceed
      passCondition = true;
    } catch (Exception e) {
      System.out.println("Integer was not entered.");
      errorMessage.setVisible(true);
    }
    if (passCondition) {
      errorMessage.setVisible(false);
      //appends (adds row) integer value in "tempvalue" to the end of the database
      SQLClass.addRecord(tempvalue);
      /*
      String subject = "[" + tempvalue + "]";
      String tutor = "[" + tempvalue + "]";
      String comment = "[" + tempvalue + "]";
      String date = "[" + tempvalue + "]";
      String time = "[" + tempvalue + "]";
      String location = "[" + tempvalue + "]";
      temporary.add(new Schedule(subject, tutor, comment, date, time, location));
      */
      //Automatically synchronizes database without having to click "Load Database"
      try {
        updateAssignments(new ActionEvent());
      } catch (IOException e) {
        e.printStackTrace();
      }
      //tempvalue++;
    }
  }

  /**
   * Updates the tree table without querying the database.
   */
  @FXML
  private void updateAssignments(ActionEvent event) throws IOException {

    //Creates "Subject" Column
    JFXTreeTableColumn<Schedule, String> subject = new JFXTreeTableColumn("Column I");
    subject.setPrefWidth(100);

    subject.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().subject;
          }
        });

    //Creates "Tutor" Column
    JFXTreeTableColumn<Schedule, String> tutor = new JFXTreeTableColumn("Column II");
    tutor.setPrefWidth(100);

    tutor.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().tutor;
          }
        });

    //Creates "Comment" Column
    JFXTreeTableColumn<Schedule, String> comment = new JFXTreeTableColumn("Column III");
    comment.setPrefWidth(100);

    comment.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>,
            ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().comment;
          }
        });

    //Creates "Date" Column
    JFXTreeTableColumn<Schedule, String> date = new JFXTreeTableColumn("Column IV");
    date.setPrefWidth(100);

    date.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>,
            ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().date;
          }
        });

    //Creates "Time" Column
    JFXTreeTableColumn<Schedule, String> time = new JFXTreeTableColumn("Column V");
    time.setPrefWidth(100);

    time.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>,
            ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().time;
          }
        });

    //Creates "Location" Column
    JFXTreeTableColumn<Schedule, String> location = new JFXTreeTableColumn("Column VI");
    location.setPrefWidth(100);

    location.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>,
            ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().location;
          }
        });

    // Appends default rows to the table views
    ObservableList<Schedule> schedule = FXCollections.observableArrayList();
    /*schedule
        .add(new Schedule("Calculus 2", "Carlos", "Integrals", "11/28/18", "3:00", "Library 203"));
    schedule.add(
        new Schedule("Calculus 1", "Hunter", "Derivatives", "11/28/18", "4:00", "Library 204"));
    schedule
        .add(new Schedule("Physics", "Brian", "2D movement", "12/03/18", "5:30", "Library 205"));
    schedule.add(new Schedule("Software", "Martin", "Loops", "12/04/18", "15:00", "Library 206"));*/

    //Finds the integer value stored at each index of the database and creates a row for each
    //element.
    try {
      System.out.println("Getting your query ready! This may take a moment...");
      int tempInt = 0;
      int tempSize = SQLClass.get().size();
      //For all of the elements in the database
      for (int counter = 0; counter < tempSize; counter++) {
        //get the integer value stored at the current index
        tempInt = Integer.parseInt(SQLClass.get().get(counter));
        //append a new row with this value in all fields
        schedule.add(
            new Schedule("[" + tempInt + "]", "[" + tempInt + "]", "[" + tempInt + "]",
                "[" + tempInt + "]", "[" + tempInt + "]",
                "[" + tempInt + "]"));
      }
    } catch (Exception e) {
      System.out.println("Update from was not successful.");
    }

    // Gets size of "temporary" which contains non-hardcoded row information
    int temporarySize = temporary.size();

    // Appends all the "temporary" rows onto the hardcoded rows in "Schedule"
    for (int i = 0; i < temporarySize; i++) {
      schedule.add(temporary.get(i));
    }

    // Takes all rows and submits them to the tree table
    final TreeItem<Schedule> root = new RecursiveTreeItem<Schedule>(schedule,
        RecursiveTreeObject::getChildren);
    table.getColumns().setAll(subject, tutor, comment, date, time, location);
    table.setRoot(root);
    table.setShowRoot(false);
  }

}
