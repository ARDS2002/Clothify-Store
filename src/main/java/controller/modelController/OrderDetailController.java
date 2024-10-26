package controller.modelController;

import dto.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean addOrderDetail(List<OrderDetail> orderDetails){
        for (OrderDetail orderDetail:orderDetails) {
            boolean isAdd= addOrderDetail(orderDetail);
            if (!isAdd){
                return false;
            }
        }
        return true;
    }
    public boolean addOrderDetail(OrderDetail orderDetails){
        String SQL = "INSERT INTO orderdetailentity VALUES(?,?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    orderDetails.getDiscount(),
                    orderDetails.getPPrice(),
                    orderDetails.getQuantity(),
                    orderDetails.getOID(),
                    orderDetails.getPID(),
                    orderDetails.getPName()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
