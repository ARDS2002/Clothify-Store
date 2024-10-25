package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class UserFormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    void btnOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnProductOnAction(ActionEvent event) {

        try {
           borderPane.setCenter(new FXMLLoader(getClass().getResource("/view/product_form.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {

    }

    @FXML
    private void btnBackOnAction(ActionEvent actionEvent) {
    }
}
