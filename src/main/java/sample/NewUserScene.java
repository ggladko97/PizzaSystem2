package sample;


import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import sample.dao.TokensDao;
import sample.dao.TokensDaoImpl;
import sample.dao.UsersDao;
import sample.dao.UsersDaoImpl;
import sample.entity.Tokens;
import sample.entity.Users;

/**
 * Created by ggladko97 on 24.06.17.
 */
public class NewUserScene implements Initializable{

  private static MessageDigest md;


  private UsersDao usersDao;
  private TokensDao tokensDao;




  public static String cryptWithMD5(String pass){
    try {
      md = MessageDigest.getInstance("MD5");
      byte[] passBytes = pass.getBytes();
      md.reset();
      byte[] digested = md.digest(passBytes);
      StringBuffer sb = new StringBuffer();
      for(int i=0;i<digested.length;i++){
        sb.append(Integer.toHexString(0xff & digested[i]));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException ex) {

    }
    return null;


  }
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
  public void createUser(ActionEvent actionEvent) {
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
              u.getPassword().equals(cryptWithMD5(password))) {
            lblResult.setText("User or password exists. Log in");
            excistingFlag = true;
          }
        }
        if (excistingFlag == false) {
          Users newUser = new Users();
          newUser.setId(1);
          newUser.setName(name);
          newUser.setEmail(mail);
          newUser.setPhone(phone);
          newUser.setPassword(cryptWithMD5(password));

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
