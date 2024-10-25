package controller;

import com.jfoenix.controls.JFXButton;
import controller.common.SceneNavigateController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class LoginFormController {

    @FXML
    private JFXButton btnAdmin;
    @FXML
    private JFXButton btnUser;
    @FXML
    private Pane paneLogin;
    @FXML
    private Label lblTitle;
    private static Stack<Scene> sceneStack = new Stack<>();

    @FXML
    public void initialize() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), lblTitle);
        fadeTransition.setFromValue(0.0); // Start fully transparent
        fadeTransition.setToValue(1.0); // End fully opaque
        fadeTransition.setCycleCount(1); // Play once
        fadeTransition.setAutoReverse(false); // No auto-reverse

        fadeTransition.play();
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), lblTitle);

        translateTransition.setFromX(0); // Starting X position
        translateTransition.setToX(100); // Ending X position (300 pixels to the right)
        translateTransition.setCycleCount(1); // Play once
        translateTransition.setAutoReverse(false); // No auto-reverse

        translateTransition.play();
    }

    public static void pushScene(Scene scene) {
        sceneStack.push(scene);
    }

    public static Scene popScene() {
        if (!sceneStack.isEmpty()) {
            return sceneStack.pop();
        }
        return null; // Or handle empty stack case
    }

    public void btnAdminOnAction(ActionEvent actionEvent) throws IOException {
        SceneNavigateController.loadForm("/view/admin_form.fxml", actionEvent);
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        SceneNavigateController.loadForm("/view/user_form.fxml", actionEvent);
    }
}