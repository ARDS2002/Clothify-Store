package controller.modelController;

import dto.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail: orderDetails){
            boolean updateStock = updateStock(orderDetail);
            if (!updateStock){
                return false;
            }
        }
        return true;
    }
    public boolean updateStock(OrderDetail orderDetails){
        String SQL = "UPDATE productentity SET pQuantity=pQuantity-? WHERE pID=?";
        try {
            return CrudUtil.execute(SQL,orderDetails.getQuantity(),orderDetails.getPID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
