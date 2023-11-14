package workflow.example.workflow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.User;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.UserRepository;
import workflow.example.workflow.service.TacheService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TacheServiceTest {

    @Mock
    private TacheRepository tacheRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TacheService tacheService;

    @Test
    void addTacheTest() {

        Tache tache = new Tache();

        ResponseEntity<Object> responseEntity = tacheService.addTache(tache);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }

    @Test
    void updateTacheTest() {
        Long tacheId = 1L;
        Tache updatedTache = new Tache();

        when(tacheRepository.findById(tacheId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            tacheService.updateTache(tacheId, updatedTache);
        });
    }

    @Test
    void desassignerTacheAUtilisateurTest() {
        Long tacheId = 1L;
        Long userId = 2L;
        Tache task = new Tache();
        User user = new User();
        task.getUserList().add(user);
        when(tacheRepository.findById(tacheId)).thenReturn(Optional.of(task));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        tacheService.desassignerTacheAUtilisateur(tacheId, userId);

        Assertions.assertTrue(task.getUserList().isEmpty());
    }

    @Test
    void testDeleteTacheById() {
        Long id = 1L;
        Tache existingTache = new Tache();
        when(tacheRepository.findById(id)).thenReturn(java.util.Optional.of(existingTache));

        tacheService.deleteTacheById(id);

        verify(tacheRepository, times(1)).delete(existingTache);
    }

    @Test
    void testGetAllTaches() {
        when(tacheRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertEquals(Collections.emptyList(), tacheService.getAlltaches());
    }
}