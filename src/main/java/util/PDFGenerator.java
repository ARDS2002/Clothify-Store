package util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import dto.Order;
import dto.OrderDetail;

import java.io.FileNotFoundException;

public class PDFGenerator {

    public void createOrderPdf(Order order, String pdfPath) {
        try {
            // Initialize PdfWriter and PdfDocument
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add Order Header Info
            document.add(new Paragraph("Order Summary").setBold().setFontSize(14));
            document.add(new Paragraph("Order ID: " + order.getOID()));
            document.add(new Paragraph("Customer Name: " + order.getCName()));
            document.add(new Paragraph("Customer Email: " + order.getCEmail()));
            document.add(new Paragraph("Payment Type: " + order.getPaymentType()));
            document.add(new Paragraph("Order Date: " + order.getDate()));
            document.add(new Paragraph("Employee ID: " + order.getEID()));
            document.add(new Paragraph("Total: Rs. " + order.getTotal()));
            document.add(new Paragraph("Discount: Rs. " + order.getDiscount()));
            document.add(new Paragraph("\n"));

            Table table = new Table(5);
            table.addCell(new Cell().add(new Paragraph("Product ID")));
            table.addCell(new Cell().add(new Paragraph("Product Name")));
            table.addCell(new Cell().add(new Paragraph("Quantity")));
            table.addCell(new Cell().add(new Paragraph("Price")));
            table.addCell(new Cell().add(new Paragraph("Discount")));

            for (OrderDetail detail : order.getOrderDetails()) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(detail.getPID()))));
                table.addCell(new Cell().add(new Paragraph(detail.getPName())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(detail.getQuantity()))));
                table.addCell(new Cell().add(new Paragraph("Rs." + detail.getPPrice())));
                table.addCell(new Cell().add(new Paragraph(detail.getDiscount() + " %")));
            }
            document.add(table);

            document.close();

            System.out.println("PDF created successfully at " + pdfPath);

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

