package controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class SceneNavigateController {

    private static final Stack<Scene> SCENE_STACK = new Stack<>();

    public static void pushScene(Scene scene) {
        SCENE_STACK.push(scene);
    }

    public static Scene popScene() {
        if (!SCENE_STACK.isEmpty()) {
            return SCENE_STACK.pop();
        }
        return null;
    }

    public static void loadForm(String relativePath, ActionEvent actionEvent) {
        Parent newSceneRoot = null;
        try {
            newSceneRoot = FXMLLoader.load(Objects.requireNonNull(SceneNavigateController.class.getResource(relativePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene newScene = new Scene(newSceneRoot);

        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        primaryStage.setScene(newScene);
        primaryStage.show();
    }
}
