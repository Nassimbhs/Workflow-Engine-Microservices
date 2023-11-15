package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.TacheAtraiterController;
import workflow.example.workflow.converter.TacheAtraiterConverter;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.service.TacheAtraiteService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TacheAtraiterControllerTest {

    @InjectMocks
    private TacheAtraiterController tacheAtraiterController;
    @Mock
    private TacheAtraiteService tacheAtraiteService;
    @Mock
    private TacheAtraiterConverter tacheAtraiterConverter;

    @Test
    void testGetTacheAtraiterByResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();

        when(tacheAtraiteService.getTacheAtraiterByResponsable(responsableId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(new ArrayList<>())).thenReturn(tacheAtraiterDtos);

        ResponseEntity<List<TacheAtraiterDto>> result = tacheAtraiterController.getTacheAtraiterByResponsable(responsableId);

        assertEquals(tacheAtraiterDtos, result.getBody());

        verify(tacheAtraiteService, times(1)).getTacheAtraiterByResponsable(responsableId);
        verify(tacheAtraiterConverter, times(1)).entityToDto(new ArrayList<>());
    }

    @Test
    void testFindByWorkflowId() {
        Long workflowId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();

        when(tacheAtraiteService.findByWorkflowId(workflowId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(new ArrayList<>())).thenReturn(tacheAtraiterDtos);

        List<TacheAtraiterDto> result = tacheAtraiterController.findByWorkflowId(workflowId);

        assertEquals(tacheAtraiterDtos, result);

        verify(tacheAtraiteService, times(1)).findByWorkflowId(workflowId);
        verify(tacheAtraiterConverter, times(1)).entityToDto(new ArrayList<>());
    }

}
