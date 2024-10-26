package controller;

import controller.common.SceneNavigateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UserFormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        SceneNavigateController.loadForm("/view/login_form.fxml", event);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        try {
            borderPane.setCenter(new FXMLLoader(getClass().getResource("/view/place_order_form.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            borderPane.setCenter(new FXMLLoader(getClass().getResource("/view/supplier_form.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
