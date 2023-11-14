package workflow.example.workflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import workflow.example.workflow.service.EmailService;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendEmail() {
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail("nassim.benhassine@esprit.tn", "Test Subject", "Test Message");

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}