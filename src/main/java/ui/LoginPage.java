package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class LoginPage extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Corrected path to FXML file
		Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(new Scene(root, 520, 400));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	}