package workflow.example.workflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.WorkflowRepository;
import workflow.example.workflow.service.WorkflowService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkflowServiceTest {
    @InjectMocks
    private WorkflowService workflowService;

    @Mock
    private WorkflowRepository workflowRepository;

    @Mock
    private TacheRepository tacheRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddWorkflow() {
        Workflow workflow = new Workflow();
        workflow.setName("Test Workflow");
        Mockito.when(workflowRepository.save(Mockito.any(Workflow.class))).thenReturn(workflow);
        ResponseEntity<Object> response = workflowService.addWorkflow(workflow);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assert responseBody != null;
        String actualMessage = (String) responseBody.get("message");
        assertEquals("Workflow successfully created!", actualMessage);
    }

    @Test
    void testAddWorkflowConflict() {
        Workflow workflow = new Workflow();
        workflow.setId(1L);
        Mockito.when(workflowRepository.existsById(workflow.getId())).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> workflowService.addWorkflow(workflow));
    }

    @Test
    void testUpdateWorkflow() {
        Long workflowId = 1L;
        Workflow existingWorkflow = new Workflow();
        existingWorkflow.setId(workflowId);

        Workflow updatedWorkflow = new Workflow();
        updatedWorkflow.setId(workflowId);
        updatedWorkflow.setName("Updated Workflow");

        Mockito.when(workflowRepository.findById(workflowId)).thenReturn(java.util.Optional.of(existingWorkflow));
        Mockito.when(workflowRepository.save(Mockito.any(Workflow.class))).thenReturn(updatedWorkflow);

        ResponseEntity<Object> response = workflowService.updateWorkflow(workflowId, updatedWorkflow);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assert responseBody != null;
        String actualMessage = (String) responseBody.get("message");

        assertEquals("Workflow successfully updated!", actualMessage);
    }
    @Test
    void testUpdateWorkflowNotFound() {
        Long id = 1L;
        Workflow updatedWorkflow = new Workflow();

        Mockito.when(workflowRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> workflowService.updateWorkflow(id, updatedWorkflow));
    }

    @Test
    void testDeleteWorkflowById() {
        Long id = 1L;
        Workflow workflow = new Workflow();

        Mockito.when(workflowRepository.findById(id)).thenReturn(Optional.of(workflow));

        workflowService.deleteWorkflowById(id);

        Mockito.verify(workflowRepository).delete(workflow);
    }

    @Test
    void testDeleteWorkflowByIdNotFound() {
        Long id = 1L;

        Mockito.when(workflowRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> workflowService.deleteWorkflowById(id));
    }

    @Test
    void testGetAllWorkflows() {
        List<Workflow> workflows = new ArrayList<>();
        Mockito.when(workflowRepository.findAll()).thenReturn(workflows);

        List<Workflow> result = workflowService.getAllWorkflows();

        assertEquals(workflows, result);
    }

    @Test
    void testFindWorkflowById() {
        Long id = 1L;
        Workflow workflow = new Workflow();

        Mockito.when(workflowRepository.findById(id)).thenReturn(Optional.of(workflow));

        Workflow result = workflowService.findWorkflowById(id);

        assertEquals(workflow, result);
    }

    @Test
    void testFindWorkflowByIdNotFound() {
        Long id = 1L;

        Mockito.when(workflowRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> workflowService.findWorkflowById(id));
    }

}