package sample;

import java.net.URL;
import java.util.List;
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
import sample.dao.TokensDao;
import sample.dao.TokensDaoImpl;
import sample.dao.UsersDao;
import sample.dao.UsersDaoImpl;
import sample.entity.Users;
import sample.utils.CryptUtil;

public class Controller implements Initializable {


  private UsersDao usersDao;
  private TokensDao tokensDao;

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
    usersDao = new UsersDaoImpl();
    tokensDao = new TokensDaoImpl();
    String login = inputLogin.getText();
    String psswd = inputPassword.getText();
    List<Users> registeredUsers = usersDao.listUsers();
    boolean loggedIn = false;
    if (rbUser.isSelected()) {
      if (rbAdmin.isSelected()) {
        loginResult.setText("Choose exactly one option");
      } else {
        if (registeredUsers.isEmpty()) {
          loginResult.setText("No users");
        } else {
          for (Users u : registeredUsers) {
            if (u.getToken() == 0) {
              registeredUsers.remove(u);
            } else {
              if (u.getName().equals(login)) {
                if (u.getPassword().equals(CryptUtil.cryptWithMD5(psswd))) {
                  loginResult.setText("Success");
                  loggedIn = true;
                  break;
                }
              }
            }
          }
        }
      }
      String message = loggedIn ? "Success" : "Try again";
      loginResult.setText(message);

    } else if (rbAdmin.isSelected()) {
      boolean adminLoggined = false;
      for (Users u : registeredUsers) {
        if (u.getToken() == 1){
          if (u.getName().equals(login)) {
            if (u.getPassword().equals(CryptUtil.cryptWithMD5(psswd))) {
              loginResult.setText("Success");
              adminLoggined = true;
              break;
            }
          }
        }
      }
      String message = adminLoggined ? "Success Admin Logged" : "Try again Admin";
      loginResult.setText(message);
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
