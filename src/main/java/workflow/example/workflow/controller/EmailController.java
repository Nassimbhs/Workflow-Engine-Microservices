package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.entity.EmailRequest;
import workflow.example.workflow.service.EmailService;

@RestController
@RequestMapping("/api/email")
@Tag(name = "Email", description = "CRUD Email")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    private JavaMailSender javaMailSender;

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService, JavaMailSender javaMailSender) {
        this.emailService = emailService;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String text = emailRequest.getText();

        emailService.sendEmail(to, subject, text);
    }

}
