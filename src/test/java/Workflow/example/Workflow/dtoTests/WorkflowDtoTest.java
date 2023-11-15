package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.WorkflowDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WorkflowDtoTest {

    @Test
    void testWorkflowDto() {
        Long id = 1L;
        String name = "SampleWorkflow";
        String description = "SampleDescription";
        String etat = "Active";
        String declencheur = "Manual";
        String webhookUrl = "http://example.com/webhook";

        // Act
        WorkflowDto workflowDto = new WorkflowDto(id, name, description, null, null, etat, declencheur, webhookUrl, null, null, null, null, null, null, null);

        assertEquals(id, workflowDto.getId());
        assertEquals(name, workflowDto.getName());
        assertEquals(description, workflowDto.getDescription());
        assertEquals(etat, workflowDto.getEtat());
        assertEquals(declencheur, workflowDto.getDeclencheur());
        assertEquals(webhookUrl, workflowDto.getWebhookUrl());
    }
}
