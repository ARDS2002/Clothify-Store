package controller;

import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.ProductDao;
import service.ServiceFactory;
import service.custom.ProductService;
import util.DaoType;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public JFXTextField txtPQty;
    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPDiscount;

    @FXML
    private TableColumn<?, ?> colPID;

    @FXML
    private TableColumn<?, ?> colPName;

    @FXML
    private TableColumn<?, ?> colPPrice;

    @FXML
    private TableColumn<?, ?> colPQty;

    @FXML
    private TableColumn<?, ?> colPTotal;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private JFXTextField txtCustomerEmail;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtProductQty;

    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
    private static Product selectedProduct;
    private static CartTM selectedCartTm;
    private static ObservableList<CartTM> cartTMList = FXCollections.observableArrayList();
    private static Double netTotal = 0.0;
    private static Double netDiscount = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCartCellValueFactory();
        setProductCellValueFactory();
        loadProductTable();

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                selectedProduct = newVal;
            }
        });
        tblCart.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                selectedCartTm = newVal;
            }
        });

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (selectedProduct != null && txtProductQty != null && !txtProductQty.getText().isEmpty()) {
            Long id = selectedProduct.getPID();
            String name = selectedProduct.getPName();
            Integer qty = Integer.valueOf(txtProductQty.getText());
            Double price = selectedProduct.getPPrice();
            Double discount = selectedProduct.getPDiscount();
            Double total = ((qty * price) * (100 - discount)) / 100;

            if (Integer.parseInt(txtProductQty.getText()) > selectedProduct.getPQuantity()) {
                new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            } else {
                cartTMList.add(new CartTM(id, name, qty, price, discount, total));
                calcNetTotal();
                calcNetDiscount();
            }
        }
        tblCart.setItems(cartTMList);
    }

    private void calcNetDiscount() {
        for (CartTM cartTM : cartTMList) {
            netDiscount += (cartTM.getPPrice() * cartTM.getPQty()) - cartTM.getTotal();
        }
    }

    private void calcNetTotal() {

        for (CartTM cartTM : cartTMList) {
            netTotal += cartTM.getTotal();
        }
    }

    @FXML
    void btnDeleteProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {
        String cName = txtCustomerName.getText();
        String cEmail = txtCustomerEmail.getText();
        String paymentType = "online";
        Double total = netTotal;
        Double discount = netDiscount;
        LocalDate date = LocalDate.now();
        Long eID = 1l;
        List<OrderDetail> orderDetail = new ArrayList<>();

        cartTMList.forEach(obj -> {
            orderDetail.add(new OrderDetail(obj.getPID(), null, obj.getPName(), obj.getPQty(), obj.getTotal(), obj.getPDiscount()));
        });

        Order order = new Order(null, cName, cEmail, paymentType, total, discount, date, eID, orderDetail);

        try {
            orderDao.placeOrder(order);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateCartOnAction(ActionEvent event) {

    }

    private void setProductCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("pID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("pSize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("pQuantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("pDiscount"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("pCategory"));
    }

    private void setCartCellValueFactory() {
        colPID.setCellValueFactory(new PropertyValueFactory<>("pID"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colPQty.setCellValueFactory(new PropertyValueFactory<>("pQty"));
        colPPrice.setCellValueFactory(new PropertyValueFactory<>("pPrice"));
        colPDiscount.setCellValueFactory(new PropertyValueFactory<>("pDiscount"));
        colPTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void loadProductTable() {

        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productObservableList.addAll(productService.getProduct());
        tblProduct.setItems(productObservableList);

    }
}
