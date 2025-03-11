package at.ac.tgm.rebay.rebay_backend.services;

import at.ac.tgm.rebay.rebay_backend.dtos.RequestProductDto;
import at.ac.tgm.rebay.rebay_backend.models.EmailDetails;
import java.io.File;
import java.util.Optional;

import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.repositories.ProductRepository;
import at.ac.tgm.rebay.rebay_backend.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public EmailService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    //zum testen benötigt: 2 user in der datenbank (1 anbieter, 1 abnehmer) und zugang zu beiden mails, testprodukt
    public String sendRequestMail(RequestProductDto requestProduct)
    {

        Product product = productRepository.findById(requestProduct.getProductId()).orElseThrow();
        User user = userRepository.findById(requestProduct.getUserId()).orElseThrow();

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage1 = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage1.setFrom(sender);
            mailMessage1.setTo(product.getOwner().getLoginContactEmail());
            mailMessage1.setText(
                            "Angefragtes Produkt: " + product.getProductName() + "\n" +
                            "Menge: " + requestProduct.getProductAmount() + "\n\n" +
                            "Angefragt von: " + user.getName() + "\n" +
                            "Kontaktdaten des Anfragenden: " + user.getLoginContactEmail() + "\n" +
                            "Telefonnummer: " + user.getContactPhonenumber() + "\n\n"
                    );
            mailMessage1.setSubject("Anfrage zum Produkt " + product.getProductName());

            // Sending the mail
            javaMailSender.send(mailMessage1);




            SimpleMailMessage mailMessage2 = new SimpleMailMessage();

            mailMessage2.setFrom(sender);
            mailMessage2.setTo(user.getLoginContactEmail());
            mailMessage2.setText(
                    "Angefragtes Produkt: " + product.getProductName() + "\n" +
                            "Menge: " + requestProduct.getProductAmount() + "\n\n" +
                            "Angefragt an: " + product.getOwner().getName() + "\n" +
                            "Kontaktdaten des Anfragenden: " + product.getOwner().getLoginContactEmail() + "\n" +
                            "Telefonnummer: " + product.getOwner().getContactPhonenumber() + "\n\n"
            );
            mailMessage2.setSubject("Bestätigung der Anfrage zum Produkt " + product.getProductName());

            javaMailSender.send(mailMessage2);


            return "Mails Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}