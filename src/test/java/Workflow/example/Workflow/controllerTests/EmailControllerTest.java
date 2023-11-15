package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.controller.EmailController;
import workflow.example.workflow.entity.EmailRequest;
import workflow.example.workflow.service.EmailService;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;
    @Mock
    private EmailService emailService;

    @Test
    void testSendEmail() {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo("recipient@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setText("Test Email Body");

        doNothing().when(emailService).sendEmail(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText()
        );

        emailController.sendEmail(emailRequest);

        verify(emailService, times(1)).sendEmail(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText()
        );
    }

}
