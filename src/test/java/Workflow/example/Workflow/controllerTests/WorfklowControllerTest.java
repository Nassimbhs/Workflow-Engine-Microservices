package workflow.example.workflow.controllerTests;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.WorfklowController;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.service.WorkflowService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorfklowControllerTest {

    @InjectMocks
    private WorfklowController worfklowController;

    @Mock
    private WorkflowService workflowService;

    @Test
    void testAddWorkflow() {
        WorkflowDto workflowDto = new WorkflowDto();

        when(workflowService.addWorkflow(workflowDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = worfklowController.addWorkflow(workflowDto);

        assertEquals(ResponseEntity.ok().build(), result);

        verify(workflowService, times(1)).addWorkflow(workflowDto);
    }


    @Test
    void testGetWorkflowTables() throws NotFoundException {
        Long workflowId = 1L;
        List<String> tables = new ArrayList<>();

        when(workflowService.getWorkflowTables(workflowId)).thenReturn(tables);

        List<String> result = worfklowController.getWorkflowTables(workflowId);

        assertEquals(tables, result);

        verify(workflowService, times(1)).getWorkflowTables(workflowId);
    }
}
