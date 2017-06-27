package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ClientController {

  @FXML
  public Label lblUserName;
  @FXML
  public ListView lvMenu;
  @FXML
  public TextArea tvDescription;
  @FXML
  public CheckBox cbCall;
  @FXML
  public Button btnOptions;
  @FXML
  public TextField promotionCode;
  @FXML
  public Button btnSubmitOrder;
  @FXML
  public Button btnClear;

  public ClientController() {
    //load data from db
  }
}
