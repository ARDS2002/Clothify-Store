package util;

import dto.Order;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;


public class EmailService {

    public static void create(String to, String subject, Order order) {
        try {

            String message = String.format(
                    "Order Details:\n" +
                            "Order ID: %d\n" +
                            "Customer Name: %s\n" +
                            "Product Price: $%.2f\n" +
                            "Discount: $%.2f\n" +
                            "Date: %s\n",
                    order.getOID(),
                    order.getCName(),
                    order.getTotal(),
                    order.getDiscount(),
                    order.getDate()
            );

            // Build the email with attachment
            Email email = EmailBuilder.startingBlank()
                    .from("Avishka", "avishka.rishal@gmail.com")
                    .to(to)
                    .withSubject(subject)
                    .withPlainText(message)
                    //.withAttachment("OrderDetails.pdf", new FileDataSource(file))
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 465, "avishka.rishal@gmail.com", "your-app-password")
                    .withTransportStrategy(TransportStrategy.SMTPS)
                    .buildMailer();

            mailer.sendMail(email);
            System.out.println("Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
