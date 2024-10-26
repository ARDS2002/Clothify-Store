package controller;

import controller.common.SceneNavigateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminFormController {

    @FXML
    private BorderPane borderpane;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        SceneNavigateController.loadForm("/view/login_form.fxml", event);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            borderpane.setCenter(new FXMLLoader(getClass().getResource("/view/add_employee_form.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        try {
            borderpane.setCenter(new FXMLLoader(getClass().getResource("/view/sales_report_form.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
