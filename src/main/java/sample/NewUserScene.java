package sample;


import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dao.TokensDao;
import sample.dao.TokensDaoImpl;
import sample.dao.UsersDao;
import sample.dao.UsersDaoImpl;
import sample.entity.Tokens;
import sample.entity.Users;
import sample.utils.CryptUtil;

/**
 * Created by ggladko97 on 24.06.17.
 */
public class NewUserScene implements Initializable{

  //private static MessageDigest md;


  private UsersDao usersDao;
  private TokensDao tokensDao;





  @FXML
  public Label lblResult;
  @FXML
  public TextField inputName;
  @FXML
  public TextField inputMail;
  @FXML
  public TextField inputPhone;
  @FXML
  public TextField inputPassword;
  @FXML
  public TextField inputToken;
  @FXML
  public Slider sliderHappiness;
  @FXML
  public Button btnCreateUser;

  @FXML
  public void createUser(ActionEvent actionEvent) throws IOException {
    usersDao = new UsersDaoImpl();
    tokensDao = new TokensDaoImpl();
    List<String> errors = new ArrayList<>();
    String name = inputName.getText();
    String mail = inputMail.getText();
    String phone = inputPhone.getText();
    String password = inputPassword.getText();
    String token = inputToken.getText();
    if (!name.matches("^[^\\\\d].*")) {
      errors.add("Name is wrong");
    }
    if (!mail.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
      errors.add("E-mail is not valid");
    }
    if (!phone.matches("\\d+")) {
      errors.add("T-phone is not valid");
    }
    if (!password.matches("\\w{8,16}")) {
      errors.add("Password length should be > 8 and < 16");
    }
    if (errors.isEmpty()) {
      System.out.println("No errors here");
        List<Users> existingUsers = usersDao.listUsers();
        boolean excistingFlag = false;
      System.out.println("Existing usrs: " + existingUsers);
        for (Users u : existingUsers) {
          if (u.getName().equals(name) ||
              u.getPassword().equals(CryptUtil.cryptWithMD5(password))) {
            lblResult.setText("User or password exists. Log in");
            excistingFlag = true;
          }
        }
        if (excistingFlag == false) {
          Users newUser = new Users();

          newUser.setName(name);
          newUser.setEmail(mail);
          newUser.setPhone(phone);
          newUser.setPassword(CryptUtil.cryptWithMD5(password));

          if (token.isEmpty()) {
            newUser.setToken((byte) 1);
          } else {
            List<Tokens> tokensList = tokensDao.listTokens();
            System.out.println(tokensList);
            boolean tknFlag = false;
            for (Tokens t : tokensList) {
              if (t.getToken().equals(token)) {
                tknFlag = true;
              }
            }
            if (tknFlag == true) {
              newUser.setToken((byte) 0);
            } else {
              newUser.setToken((byte) 1);
            }
          }
          System.out.println(newUser);
          usersDao.insert(newUser);
          lblResult.setText("Authentication succeed");

          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
          System.out.println("LOADER LOADED");
          Parent root1 = (Parent) fxmlLoader.load();
          Stage stage = new Stage();
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.setTitle("Log in");
          stage.setScene(new Scene(root1));
          System.out.println("STAGE SET NEw SCENE");
          stage.show();
          Stage stageOld = (Stage) btnCreateUser.getScene().getWindow();
          stageOld.close();
          System.out.println("STAGE SHOW");

        } else {
          lblResult.setText("New user creation failed. Try again");
        }

    } else {
      //show report
    }

    System.out.println("create user btn" );
  }

  @Override public void initialize(URL location, ResourceBundle resources) {
    System.out.println("INITIALIZE NEW SCENE KURWA NEVER");

  }
}
