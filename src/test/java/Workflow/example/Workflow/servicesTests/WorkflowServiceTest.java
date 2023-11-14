package workflow.example.workflow.servicesTests;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.converter.WorkflowConverter;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.repository.WorkflowRepository;
import workflow.example.workflow.service.TableService;
import workflow.example.workflow.service.WorkflowService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkflowServiceTest {

    @Mock
    private WorkflowRepository workflowRepository;

    @Mock
    private WorkflowConverter workflowConverter;
    @Mock
    private TableService tableService;
    @InjectMocks
    private WorkflowService workflowService;

    @Test
    void testUpdateWorkflow() {
        Long id = 1L;
        WorkflowDto workflowDto = new WorkflowDto();
        Workflow existingWorkflow = new Workflow();
        Workflow updatedWorkflow = new Workflow();
        when(workflowConverter.dtoToEntity(workflowDto)).thenReturn(updatedWorkflow);
        when(workflowRepository.findById(id)).thenReturn(Optional.of(existingWorkflow));

        ResponseEntity<Object> response = workflowService.updateWorkflow(id, workflowDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteWorkflowById() {
        Long id = 1L;
        Workflow workflow = new Workflow();
        when(workflowRepository.findById(id)).thenReturn(Optional.of(workflow));

        workflowService.deleteWorkflowById(id);

        verify(workflowRepository, times(1)).delete(workflow);
    }

    @Test
    void testGetAllWorkflows() {
        List<Workflow> workflows = new ArrayList<>();
        when(workflowRepository.findAll()).thenReturn(workflows);

        List<Workflow> result = workflowService.getAllWorkflows();

        assertEquals(workflows, result);
    }

    @Test
    void testFindWorkflowById() {
        Long id = 1L;
        Workflow workflow = new Workflow();
        when(workflowRepository.findById(id)).thenReturn(Optional.of(workflow));

        Workflow result = workflowService.findWorkflowById(id);

        assertEquals(workflow, result);
    }

    @Test
    void testGetWorkflowTables() throws NotFoundException {
        Long workflowId = 1L;
        Workflow workflow = new Workflow();
        when(workflowRepository.findById(workflowId)).thenReturn(Optional.of(workflow));
        lenient().when(tableService.getTables(anyString())).thenReturn(new ArrayList<>());

        List<String> result = workflowService.getWorkflowTables(workflowId);

        assertNotNull(result);
    }

}
