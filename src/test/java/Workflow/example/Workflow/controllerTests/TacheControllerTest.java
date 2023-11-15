package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.TacheController;
import workflow.example.workflow.converter.TacheConverter;
import workflow.example.workflow.dto.TacheDto;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.service.TacheService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TacheControllerTest {

    @InjectMocks
    private TacheController tacheController;

    @Mock
    private TacheService tacheService;

    @Mock
    private TacheConverter tacheConverter;

    @Test
    void testAddTache() {
        Tache tache = new Tache();

        when(tacheService.addTache(tache)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = tacheController.addTache(tache);

        assertEquals(ResponseEntity.ok().build(), result);

        verify(tacheService, times(1)).addTache(tache);
    }


    @Test
    void testGetTasksByUser() {
        Long userId = 1L;
        List<Tache> tasks = new ArrayList<>();

        when(tacheService.getTasksByUser(userId)).thenReturn(tasks);
        when(tacheConverter.entityToDto(tasks)).thenReturn(new ArrayList<>());

        List<TacheDto> result = tacheController.getTasksByUser(userId);

        assertEquals(new ArrayList<>(), result);

        verify(tacheService, times(1)).getTasksByUser(userId);
        verify(tacheConverter, times(1)).entityToDto(tasks);
    }

}