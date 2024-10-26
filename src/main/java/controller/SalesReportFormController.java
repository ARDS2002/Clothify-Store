package controller;

import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

import java.util.List;

public class SalesReportFormController {

    public PieChart chrtSalesPie;
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    private static List<Product> productList;
    private static Integer gentsCount = 0;
    private static Integer ladiesCount = 0;
    private static Integer kidsCount = 0;

    public void initialize() {
        productList = productService.getProduct();
        productList.forEach(o -> {
            if (o.getPCategory().equalsIgnoreCase("Gents")) {
                gentsCount += o.getPQuantity();
            } else if (o.getPCategory().equalsIgnoreCase("Kids")) {
                kidsCount += o.getPQuantity();
            } else if (o.getPCategory().equalsIgnoreCase("Ladies")) {
                ladiesCount += o.getPQuantity();
            }
        });

        ObservableList<PieChart.Data> salesData = FXCollections.observableArrayList(
                new PieChart.Data("Gents", gentsCount),
                new PieChart.Data("Ladies", ladiesCount),
                new PieChart.Data("Kids", kidsCount)
        );

        chrtSalesPie.setData(salesData);
        chrtSalesPie.setTitle("Sales by Category");
    }
}
