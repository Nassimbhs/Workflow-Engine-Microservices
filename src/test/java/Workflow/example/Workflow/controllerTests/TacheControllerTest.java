package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.TacheController;
import workflow.example.workflow.converter.TacheConverter;
import workflow.example.workflow.converter.UserConverter;
import workflow.example.workflow.dto.TacheDto;
import workflow.example.workflow.dto.UserDto;
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
    @Mock
    private UserConverter userConverter;

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
    @Test
    void testAssignGroupToTask() {
        // Arrange
        Long groupId = 1L;
        Long taskId = 2L;

        tacheController.assignGroupToTask(groupId, taskId);

        verify(tacheService).assignUsersFromGroupToTask(groupId, taskId);
    }

    @Test
    void testFindByUserIdtraite() {
        Long userId = 1L;
        List<Tache> taches = new ArrayList<>();
        when(tacheService.findByUserIdtraite(userId)).thenReturn(taches);
        when(tacheConverter.entityToDto(taches)).thenReturn(new ArrayList<>());

        List<TacheDto> result = tacheController.findByUserIdtraite(userId);

        Assertions.assertNotNull(result);
        verify(tacheService).findByUserIdtraite(userId);
        verify(tacheConverter).entityToDto(taches);
    }

    @Test
    void testDesassignerTacheAUtilisateur() {
        Long tacheId = 1L;
        Long userId = 2L;

        ResponseEntity<Void> result = tacheController.desassignerTacheAUtilisateur(tacheId, userId);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(tacheService).desassignerTacheAUtilisateur(tacheId, userId);
    }

    @Test
    void testGetUtilisateursDeTache() {
        long tacheId = 1L;
        List<UserDto> userDtos = new ArrayList<>();
        when(userConverter.entityToDto(anyList())).thenReturn(userDtos);

        List<UserDto> result = tacheController.getUtilisateursDeTache(tacheId);

        Assertions.assertNotNull(result);
        assertEquals(userDtos, result);
        verify(userConverter).entityToDto(anyList());
        verify(tacheService).getUtilisateursDeTache(tacheId);
    }

    @Test
    void testAssignerTacheAUtilisateurs() {
        Long tacheId = 1L;
        Long workflowId = 2L;
        List<Long> userIds = new ArrayList<>();

        ResponseEntity<Void> result = tacheController.assignerTacheAUtilisateurs(tacheId, workflowId, userIds);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(tacheService).assignerTacheAUtilisateurs(tacheId, userIds, workflowId);
    }
}