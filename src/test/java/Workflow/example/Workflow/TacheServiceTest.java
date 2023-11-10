package workflow.example.workflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.UserRepository;
import workflow.example.workflow.service.TacheService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TacheServiceTest {

    @InjectMocks
    private TacheService tacheService;

    @Mock
    private TacheRepository tacheRepository;
    @Mock
    private TacheAtraiteRepository tacheAtraiteRepository;
    @Mock
    private UserRepository userRepository;

    @Mock
    private WebClient webClient;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTache() {
        Tache tache = new Tache();
        when(tacheRepository.existsById(any())).thenReturn(false);
        when(tacheRepository.save(tache)).thenReturn(tache);

        ResponseEntity<Object> response = tacheService.addTache(tache);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddTacheConflict() {
        Tache tache = new Tache();
        tache.setId(1L);
        when(tacheRepository.existsById(1L)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> tacheService.addTache(tache));
    }

}