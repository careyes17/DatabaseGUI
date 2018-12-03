package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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