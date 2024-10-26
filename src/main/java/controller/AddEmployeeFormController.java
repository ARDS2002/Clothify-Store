package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeContact;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtSearchEmployeeByID;

    static EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                employee = newVal;
            }
        });
        setCellValueFactory();
        loadTable();

    }

    private void setFields(Employee newVal) {
        txtEmployeeID.setText(String.valueOf(newVal.getEID()));
        txtEmployeeEmail.setText(newVal.getEEmail());
        txtEmployeeAddress.setText(newVal.getEAddress());
        txtEmployeeContact.setText(newVal.getEContact());
        txtEmployeeName.setText(newVal.getEName());
    }

    @FXML
    void btnDeleteEmplyeeOnAction(ActionEvent event) {
        employeeService.deleteEmployee(employee);
    }

    @FXML
    void btnSaveEmplyeeOnAction(ActionEvent event) {
        addEmployee();
    }

    @FXML
    void btnSearchEmplyeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateEmplyeeOnAction(ActionEvent event) {
        employee.setEAddress(txtEmployeeAddress.getText());
        employee.setEEmail(txtEmployeeEmail.getText());
        employee.setEName(txtEmployeeName.getText());
        employee.setEContact(txtEmployeeContact.getText());
        employeeService.updateEmployee(employee);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("eID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("eName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("eContact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("eEmail"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("eAddress"));
    }

    public static void initializeSampleEmployees() {

        List<Employee> sampleEmployees = new ArrayList<>();

        sampleEmployees.add(new Employee(
                null,
                "Arjuna Rishal",
                "0719098027",
                "avishka.rishal@gmail.com",
                "Panadura",
                "ards123"
        ));

        sampleEmployees.add(new Employee(
                null,
                "Anuhas Perera",
                "0719098028",
                "avishka.rishal@gmail.com",
                "Panadura",
                "ard123"
        ));

        sampleEmployees.forEach(obj -> {
            if (employeeService.addEmployee(obj)) {
                //new Alert(Alert.AlertType.INFORMATION, "Customer Added !!").show();
            } else {
                //new Alert(Alert.AlertType.ERROR, "Customer Not Added :(").show();
            }
        });
    }

    private void loadTable() {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        employeeObservableList.addAll(employeeService.getEmployee());
        tblEmployee.setItems(employeeObservableList);

    }

    private void addEmployee() {

        if (!(txtEmployeeName.getText().isEmpty()
                && txtEmployeeContact.getText().isEmpty()
                && txtEmployeeAddress.getText().isEmpty()
                && txtEmployeeEmail.getText().isEmpty())) {

            employeeService.addEmployee(new Employee(
                    null,
                    txtEmployeeName.getText(),
                    txtEmployeeContact.getText(),
                    txtEmployeeEmail.getText(),
                    txtEmployeeAddress.getText(),
                    "123456"
            ));
        }

    }
}
