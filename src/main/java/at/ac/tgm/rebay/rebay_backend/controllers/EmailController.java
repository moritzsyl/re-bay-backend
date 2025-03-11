package at.ac.tgm.rebay.rebay_backend.controllers;


import at.ac.tgm.rebay.rebay_backend.dtos.RequestProductDto;
import at.ac.tgm.rebay.rebay_backend.models.EmailDetails;
import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }


    @PostMapping("/requestProduct")
    public String requestProduct(@RequestBody RequestProductDto requestProduct) {
        {
            String status
                    = emailService.sendRequestMail(requestProduct);

            return status;
        }
    }

}