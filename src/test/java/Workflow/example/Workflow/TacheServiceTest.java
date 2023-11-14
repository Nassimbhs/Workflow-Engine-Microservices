package workflow.example.workflow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.User;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.UserRepository;
import workflow.example.workflow.service.TacheService;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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

}