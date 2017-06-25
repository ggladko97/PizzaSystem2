package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {


  @FXML
  public Button btnNewUser;
  @FXML
  public Button btnSignIn;
  @FXML
  public Label loginResult;
  @FXML
  private TextField inputLogin;
  @FXML
  private TextField inputPassword;
  @FXML
  private RadioButton rbUser;
  @FXML
  private RadioButton rbAdmin;


  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  public void signIn(ActionEvent actionEvent) {

    if (rbUser.isSelected()) {
      if (rbAdmin.isSelected()) {
        loginResult.setText("Choose exactly one option");
      } else {
        //perform log in logic for user
      }
    } else if (rbAdmin.isSelected()) {
      //perform log in logic for admin
    } else {
      loginResult.setText("Choose Admin or User field");
    }

  }

  @FXML
  public void addNewUser(ActionEvent actionEvent) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/new-user.fxml"));
    System.out.println("LOADER LOADED");
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("NEW USER");
    stage.setScene(new Scene(root1));
    System.out.println("STAGE SET SCENE");
    stage.show();
    Stage stageOld = (Stage) btnNewUser.getScene().getWindow();
    stageOld.close();
    System.out.println("STAGE SHOW");
  }

}
