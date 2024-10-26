package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtSupplierCompany;

    @FXML
    private JFXTextField txtSupplierContact;

    @FXML
    private JFXTextField txtSupplierName;
    static SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    Supplier supplier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                supplier = newVal;
            }
        });
        setCellValueFactory();
        loadTable();

    }

    private void setFields(Supplier newVal) {

        txtSupplierName.setText(newVal.getSName());
        txtSupplierContact.setText(newVal.getSContact());
        txtSupplierCompany.setText(newVal.getSCompany());

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        supplierService.deleteSupplier(supplier);
    }

    @FXML
    void btnSaveSupplierOnAction(ActionEvent event) {
        addSupplier();
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        supplier.setSCompany(txtSupplierCompany.getText());
        supplier.setSContact(txtSupplierContact.getText());
        supplier.setSName(txtSupplierName.getText());
        supplierService.updateSupplier(supplier);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("sID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("sContact"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("sCompany"));
    }

    public static void initializeSampleSuppliers() {

        List<Supplier> sampleSuppliers = new ArrayList<>();

        sampleSuppliers.add(new Supplier(null, "Kasun Perera", "KCC", "0703335599"));
        sampleSuppliers.add(new Supplier(null, "Otara Del", "ODEL", "0703335588"));
        sampleSuppliers.add(new Supplier(null, "Sohan Silva", "FOU", "0703335566"));

        sampleSuppliers.forEach(obj -> {
            if (supplierService.addSupplier(obj)) {
                //new Alert(Alert.AlertType.INFORMATION, "Customer Added !!").show();
            } else {
                //new Alert(Alert.AlertType.ERROR, "Customer Not Added :(").show();
            }
        });
    }

    private void loadTable() {

        ObservableList<Supplier> customerObservableList = FXCollections.observableArrayList();
        customerObservableList.addAll(supplierService.getSupplier());
        tblSupplier.setItems(customerObservableList);

    }

    private void addSupplier() {
        if (!(txtSupplierName.getText().isEmpty()
                && txtSupplierContact.getText().isEmpty()
                && txtSupplierCompany.getText().isEmpty())) {
            if (supplierService.addSupplier(new Supplier(
                    null,
                    txtSupplierName.getText(),
                    txtSupplierCompany.getText(),
                    txtSupplierContact.getText()
            ))) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Added !!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Added :(").show();
            }
        }
    }
}

