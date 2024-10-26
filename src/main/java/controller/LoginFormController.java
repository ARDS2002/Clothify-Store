package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.common.ProductFormController;
import controller.common.SceneNavigateController;
import dto.Employee;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class LoginFormController {


    @FXML
    private Pane paneLogin;
    @FXML
    private Label lblTitle;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    private static Stack<Scene> sceneStack = new Stack<>();

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnAdminLoginOnAction(ActionEvent event) {
        SceneNavigateController.loadForm("/view/admin_form.fxml", event);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        List<Employee> employees = employeeService.getEmployee();
        boolean isValidUser = false;
        for (Employee employee : employees) {
            if (employee.getEEmail().equals(txtEmail.getText()) && employee.getEPassword().equals(txtPassword.getText())) {
                SceneNavigateController.loadForm("/view/user_form.fxml", event);
                isValidUser = true;
                break;
            }

        }
        if (!isValidUser) {
            new Alert(Alert.AlertType.ERROR, "Wrong Inputs!").show();
        }
    }

    @FXML
    public void initialize() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(15), lblTitle);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

        fadeTransition.play();
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(15), lblTitle);

        translateTransition.setFromX(0);
        translateTransition.setToX(100);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);

        translateTransition.play();

        ProductFormController.initializeSampleProducts();
        AddEmployeeFormController.initializeSampleEmployees();
        SupplierFormController.initializeSampleSuppliers();
    }

    public static void pushScene(Scene scene) {
        sceneStack.push(scene);
    }

    public static Scene popScene() {
        if (!sceneStack.isEmpty()) {
            return sceneStack.pop();
        }
        return null;
    }


}