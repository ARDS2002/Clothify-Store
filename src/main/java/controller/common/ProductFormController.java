package controller.common;

import com.jfoenix.controls.JFXTextField;
import dto.Product;
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
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colBPrice;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProfit;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private JFXTextField txtProductBPrice;

    @FXML
    private JFXTextField txtProductCategory;

    @FXML
    private JFXTextField txtProductDiscount;

    @FXML
    private JFXTextField txtProductID;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtProductProfit;

    @FXML
    private JFXTextField txtProductQty;

    @FXML
    private JFXTextField txtProductSPrice;

    @FXML
    private JFXTextField txtProductSearchByID;

    @FXML
    private JFXTextField txtProductSize;

    @FXML
    private JFXTextField txtProductSupplier;

    static ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                product = newVal;
            }
        });

        setCellValueFactory();
        loadTable();

    }

    private void setFields(Product newValue) {
        txtProductID.setText(String.valueOf(newValue.getPID()));
        txtProductName.setText(newValue.getPName());
        txtProductSize.setText(newValue.getPSize());
        txtProductSupplier.setText(newValue.getPSupplier());
        txtProductQty.setText(String.valueOf(newValue.getPQuantity()));
        txtProductBPrice.setText(String.valueOf(newValue.getPBuyingPrice()));
        txtProductSPrice.setText(String.valueOf(newValue.getPPrice()));
        txtProductDiscount.setText(String.valueOf(newValue.getPDiscount()));
        txtProductProfit.setText(String.valueOf(newValue.getPProfit()));
        txtProductCategory.setText(newValue.getPCategory());
    }

    @FXML
    void btnSaveProductOnAction(ActionEvent actionEvent) {
        addProduct();
    }

    @FXML
    void btnUpdateProductOnAction(ActionEvent actionEvent) {
        product.setPName(txtProductName.getText());
        product.setPSize(txtProductSize.getText());
        product.setPSupplier(txtProductSupplier.getText());
        product.setPQuantity(Integer.parseInt(txtProductQty.getText()));
        product.setPBuyingPrice(Double.parseDouble(txtProductBPrice.getText()));
        product.setPPrice(Double.parseDouble(txtProductSPrice.getText()));
        product.setPDiscount(Double.parseDouble(txtProductDiscount.getText()));
        product.setPCategory(txtProductCategory.getText());
        productService.updateProduct(product);
    }

    @FXML
    void btnDeleteProductOnAction(ActionEvent actionEvent) {

        productService.deleteProduct(product);
        loadTable();
    }

    @FXML
    void btnSearchProductOnAction(ActionEvent actionEvent) {
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("pID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("pSize"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("pSupplier"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("pQuantity"));
        colBPrice.setCellValueFactory(new PropertyValueFactory<>("pBuyingPrice"));
        colSPrice.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("pDiscount"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("pProfit"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("pCategory"));
    }

    public static void initializeSampleProducts() {

        List<Product> sampleProducts = new ArrayList<>();

        sampleProducts.add(new Product(
                null,
                "Trouser",
                "L",
                "ODEL",
                50,
                3200.0,
                4850.0,
                10.0,
                500.0,
                "Gents"));

        sampleProducts.add(new Product(
                null,
                "Trouser",
                "M",
                "ODEL",
                50,
                3000.0,
                4650.0,
                10.0,
                500.0,
                "Ladies"));

        sampleProducts.forEach(obj -> {
            if (productService.addProduct(obj)) {
                //new Alert(Alert.AlertType.INFORMATION, "Customer Added !!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Not Added :(").show();
            }
        });
    }

    private void loadTable() {

        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productObservableList.addAll(productService.getProduct());
        tblProduct.setItems(productObservableList);

    }

    private void addProduct() {
        if (!(txtProductName.getText().isEmpty()
                && txtProductSize.getText().isEmpty()
                && txtProductSupplier.getText().isEmpty()
                && txtProductQty.getText().isEmpty()
                && txtProductBPrice.getText().isEmpty()
                && txtProductSPrice.getText().isEmpty()
                && txtProductDiscount.getText().isEmpty()
                && txtProductProfit.getText().isEmpty()
                && txtProductCategory.getText().isEmpty())) {
            if (productService.addProduct((new Product(
                    null,
                    txtProductName.getText(),
                    txtProductSize.getText(),
                    txtProductSupplier.getText(),
                    Integer.parseInt(txtProductQty.getText()),
                    Double.parseDouble(txtProductBPrice.getText()),
                    Double.parseDouble(txtProductSPrice.getText()),
                    Double.parseDouble(txtProductDiscount.getText()),
                    Double.parseDouble(txtProductSPrice.getText()) - Double.parseDouble(txtProductBPrice.getText()),
                    txtProductCategory.getText()
            )))) {
                new Alert(Alert.AlertType.INFORMATION, "Product Added !!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Not Added :(").show();
            }

        }
    }
}
