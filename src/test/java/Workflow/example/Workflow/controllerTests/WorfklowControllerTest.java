package workflow.example.workflow.controllerTests;

import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.WorfklowController;
import workflow.example.workflow.converter.WorkflowConverter;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
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

    @Mock
    private WorkflowConverter workflowConverter;
    @Test
    void testAddWorkflow() {
        WorkflowDto workflowDto = new WorkflowDto();
        when(workflowService.addWorkflow(workflowDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = worfklowController.addWorkflow(workflowDto);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(workflowService).addWorkflow(workflowDto);
    }

    @Test
    void testUpdateWorkflow() {
        Long id = 1L;
        WorkflowDto workflowDto = new WorkflowDto();
        when(workflowService.updateWorkflow(id, workflowDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = worfklowController.updateWorkflow(id, workflowDto);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(workflowService).updateWorkflow(id, workflowDto);
    }

    @Test
    void testDeleteWorkflow() {
        Long id = 1L;

        worfklowController.deleteWorkflow(id);

        verify(workflowService).deleteWorkflowById(id);
    }

    @Test
    void testFindAll() {
        List<WorkflowDto> workflowDtos = new ArrayList<>();
        when(workflowService.getAllWorkflows()).thenReturn(new ArrayList<>());
        when(workflowConverter.entityToDto(anyList())).thenReturn(workflowDtos);

        List<WorkflowDto> result = worfklowController.findAll();

        Assertions.assertNotNull(result);
        assertEquals(workflowDtos, result);
        verify(workflowService).getAllWorkflows();
        verify(workflowConverter).entityToDto(anyList());
    }

    @Test
    void testFindWorkflowById() {
        Long id = 1L;
        WorkflowDto workflowDto = new WorkflowDto();
        when(workflowService.findWorkflowById(id)).thenReturn(new Workflow());
        when(workflowConverter.entityToDto((Workflow) any())).thenReturn(workflowDto);

        WorkflowDto result = worfklowController.findEmployeeById(id);

        Assertions.assertNotNull(result);
        assertEquals(workflowDto, result);
        verify(workflowService).findWorkflowById(id);
        verify(workflowConverter).entityToDto((Workflow) any());
    }

    @Test
    void testGetWorkflowTables() throws NotFoundException {
        Long workflowId = 1L;
        List<String> tables = new ArrayList<>();
        when(workflowService.getWorkflowTables(workflowId)).thenReturn(new ArrayList<>());

        List<String> result = worfklowController.getWorkflowTables(workflowId);

        Assertions.assertNotNull(result);
        assertEquals(tables, result);
        verify(workflowService).getWorkflowTables(workflowId);
    }
}
