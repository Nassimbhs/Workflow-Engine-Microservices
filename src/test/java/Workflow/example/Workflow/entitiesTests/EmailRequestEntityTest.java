package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.EmailRequest;

@ExtendWith(MockitoExtension.class)
class EmailRequestEntityTest {

    @Test
    void testEmailRequestGettersAndSetters() {
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setTo("nassim.benhassine@esprit.tn");
        emailRequest.setSubject("Test Subject");
        emailRequest.setText("Hello, this is a test email!");

        Assertions.assertEquals("nassim.benhassine@esprit.tn", emailRequest.getTo());
        Assertions.assertEquals("Test Subject", emailRequest.getSubject());
        Assertions.assertEquals("Hello, this is a test email!", emailRequest.getText());
    }
}
