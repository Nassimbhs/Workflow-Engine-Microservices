package workflow.example.workflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.converter.WorkflowConverter;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.repository.TacheRepository;
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
    private TacheRepository tacheRepository;

    @Mock
    private TableService tableService;

    @Mock
    private WorkflowConverter workflowConverter;

    @InjectMocks
    private WorkflowService workflowService;

    @Test
    void testAddWorkflow() {
        WorkflowDto workflowDto = new WorkflowDto();
        Workflow workflow = new Workflow();

        lenient().when(workflowConverter.dtoToEntity(any(WorkflowDto.class))).thenReturn(workflow);
        lenient().when(workflowRepository.existsById(anyLong())).thenReturn(false);
        lenient().when(workflowRepository.save(any(Workflow.class))).thenReturn(workflow);

        ResponseEntity<Object> responseEntity = workflowService.addWorkflow(workflowDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
    @Test
    void testUpdateWorkflow() {
        Long id = 1L;
        WorkflowDto workflowDto = new WorkflowDto();
        Workflow existingWorkflow = new Workflow();
        Workflow updatedWorkflow = new Workflow();

        when(workflowConverter.dtoToEntity(any(WorkflowDto.class))).thenReturn(updatedWorkflow);
        when(workflowRepository.findById(id)).thenReturn(Optional.of(existingWorkflow));
        when(workflowRepository.save(any(Workflow.class))).thenReturn(updatedWorkflow);

        ResponseEntity<Object> responseEntity = workflowService.updateWorkflow(id, workflowDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testUpdateWorkflowNotFound() {
        Long id = 1L;
        WorkflowDto workflowDto = new WorkflowDto();

        when(workflowConverter.dtoToEntity(any(WorkflowDto.class))).thenReturn(new Workflow());
        when(workflowRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> workflowService.updateWorkflow(id, workflowDto));
    }

    @Test
    void testDeleteWorkflowById() {
        Long id = 1L;
        Workflow existingWorkflow = new Workflow();

        when(workflowRepository.findById(id)).thenReturn(Optional.of(existingWorkflow));

        workflowService.deleteWorkflowById(id);

        // Verify that delete method was called
        verify(workflowRepository, times(1)).delete(existingWorkflow);
    }

    @Test
    void testDeleteWorkflowByIdNotFound() {
        Long id = 1L;

        when(workflowRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> workflowService.deleteWorkflowById(id));
    }

}
