package repository.custom.impl;

import controller.modelController.OrderDetailController;
import controller.modelController.ProductController;
import db.DBConnection;
import dto.Order;
import dto.OrderDetail;
import entity.OrderEntity;
import javafx.scene.control.Alert;
import repository.custom.OrderDao;
import util.EmailService;
import util.PDFGenerator;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(Long id) {
        return null;
    }

    @Override
    public boolean placeOrder(Order order) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String SQL = "INSERT INTO orderentity (date, discount, total, eID, cEmail, cName, paymentType) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection.setAutoCommit(false);

            PreparedStatement psTm = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psTm.setObject(1, order.getDate());
            psTm.setObject(2, order.getDiscount());
            psTm.setObject(3, order.getTotal());
            psTm.setObject(4, order.getEID());
            psTm.setObject(5, order.getCEmail());
            psTm.setObject(6, order.getCName());
            psTm.setObject(7, order.getPaymentType());

            boolean isOrderAdd = psTm.executeUpdate() > 0;
            if (isOrderAdd) {
                ResultSet generatedKeys = psTm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long generatedOrderId = generatedKeys.getLong(1);

                    for (OrderDetail detail : order.getOrderDetails()) {
                        detail.setOID(generatedOrderId);
                    }
                    boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                    if (isOrderDetailAdd) {
                        boolean isUpdateStock = new ProductController().updateStock(order.getOrderDetails());
                        if (isUpdateStock) {
                            connection.commit();
                            new Alert(Alert.AlertType.INFORMATION, "Order Placed !!").show();
                            //EmailService.create(order.getCEmail(),"Order Receipt",order);
                            PDFGenerator pdfGenerator = new PDFGenerator();
                            pdfGenerator.createOrderPdf(order, "C:/Users/Avishka de Silva/OneDrive/Pictures/pdf/order_summary.pdf");
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
