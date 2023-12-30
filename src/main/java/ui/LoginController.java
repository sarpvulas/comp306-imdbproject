package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private ImageView exitButton;

    public void exitButtonAction(MouseEvent event) {

        Stage stage = (Stage) exitButton.getScene().getWindow();



        stage.close();

    }


}
