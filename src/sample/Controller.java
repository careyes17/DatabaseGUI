package sample;

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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

public class Controller {

  @FXML
  JFXTreeTableView table;

  // Temporary ObservableList that is appended to the default values preloaded
  ObservableList<Schedule> temporary = FXCollections.observableArrayList();

  // Shared variable that makes "appendRow" append a consecutive integer to make the functionality more apparent
  static int tempvalue;

  /**
   * Takes an integer value from a remote database and sets that as a default value for the
   * appending rows, then appends them.
   */
  @FXML
  private void appendRow(ActionEvent event) throws Exception {
    String subject = "[" + tempvalue + "]";
    String tutor = "[" + tempvalue + "]";
    String comment = "[" + tempvalue + "]";
    String date = "[" + tempvalue + "]";
    String time = "[" + tempvalue + "]";
    String location = "[" + tempvalue + "]";
    temporary.add(new Schedule(subject, tutor, comment, date, time, location));
    try {
      updateAssignments(new ActionEvent());
    } catch (IOException e) {
      e.printStackTrace();
    }
    tempvalue++;
  }

  /**
   * Updates the tree table without querying the database.
   */
  @FXML
  private void updateAssignments(ActionEvent event) throws IOException {

    JFXTreeTableColumn<Schedule, String> subject = new JFXTreeTableColumn("Subject");
    subject.setPrefWidth(100);

    subject.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().subject;
          }
        });

    //subject, tutor, comment, date, time, location

    JFXTreeTableColumn<Schedule, String> tutor = new JFXTreeTableColumn("Tutor");
    tutor.setPrefWidth(100);

    tutor.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().tutor;
          }
        });

    JFXTreeTableColumn<Schedule, String> comment = new JFXTreeTableColumn("Comment");
    comment.setPrefWidth(100);

    comment.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().comment;
          }
        });

    JFXTreeTableColumn<Schedule, String> date = new JFXTreeTableColumn("Date");
    date.setPrefWidth(100);

    date.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().date;
          }
        });

    JFXTreeTableColumn<Schedule, String> time = new JFXTreeTableColumn("Time");
    time.setPrefWidth(100);

    time.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().time;
          }
        });

    JFXTreeTableColumn<Schedule, String> location = new JFXTreeTableColumn("Location");
    location.setPrefWidth(100);

    location.setCellValueFactory(
        new Callback<TreeTableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              TreeTableColumn.CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().location;
          }
        });

    ObservableList<Schedule> Schedule = FXCollections.observableArrayList();
    Schedule
        .add(new Schedule("Calculus 2", "Carlos", "Integrals", "11/28/18", "3:00", "Library 203"));
    Schedule.add(
        new Schedule("Calculus 1", "Hunter", "Derivatives", "11/28/18", "4:00", "Library 204"));
    Schedule
        .add(new Schedule("Physics", "Brian", "2D movement", "12/03/18", "5:30", "Library 205"));
    Schedule.add(new Schedule("Software", "Martin", "Loops", "12/04/18", "15:00", "Library 206"));

    int temporarySize = temporary.size();

    for (int i = 0; i < temporarySize; i++) {
      Schedule.add(temporary.get(i));
    }

    final TreeItem<Schedule> root = new RecursiveTreeItem<Schedule>(Schedule,
        RecursiveTreeObject::getChildren);
    table.getColumns().setAll(subject, tutor, comment, date, time, location);
    table.setRoot(root);
    table.setShowRoot(false);
  }

  /**
   * Deals with populating the ObservableList array with fields.
   */
  class Schedule extends RecursiveTreeObject<Schedule> {

    StringProperty subject;
    StringProperty tutor;
    StringProperty comment;
    StringProperty date;
    StringProperty time;
    StringProperty location;

    public Schedule(String subject, String tutor, String comment, String date, String time,
        String location) {
      this.subject = new SimpleStringProperty(subject);
      this.tutor = new SimpleStringProperty(tutor);
      this.comment = new SimpleStringProperty(comment);
      this.date = new SimpleStringProperty(date);
      this.time = new SimpleStringProperty(time);
      this.location = new SimpleStringProperty(location);
    }


  }

}
