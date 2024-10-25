import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bootstrap extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/login_form.fxml")));
        scene.getStylesheets().add(getClass().getResource("css/loginFormStyle.css").toExternalForm());
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.setTitle("Clothify Store | DASHBOARD FORM");
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();
    }
}
