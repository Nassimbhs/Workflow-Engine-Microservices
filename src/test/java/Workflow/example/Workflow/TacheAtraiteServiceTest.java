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
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.service.TacheAtraiteService;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TacheAtraiteServiceTest {

    @InjectMocks
    private TacheAtraiteService tacheAtraiteService;

    @Mock
    private TacheAtraiteRepository tacheAtraiteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMarquerTacheCommeTraite() {
        Long id = 1L;
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        tacheAtraiter.setId(id);

        Mockito.when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.of(tacheAtraiter));
        Mockito.when(tacheAtraiteRepository.save(tacheAtraiter)).thenReturn(tacheAtraiter);

        ResponseEntity<Object> response = tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiter);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        assertEquals("tacheAtraiter successfully updated!", responseBody.get("message"));
        assertEquals("traité", tacheAtraiter.getStatut());
        assertEquals("Accepter", tacheAtraiter.getApprobation());
    }


    @Test
    public void testMarquerTacheCommeTraiteNotFound() {
        Long id = 1L;
        TacheAtraiter tacheAtraiter = new TacheAtraiter();

        Mockito.when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiter));
    }

    @Test
    public void testRejeterTache() {
        Long id = 1L;
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        tacheAtraiter.setId(id);

        Mockito.when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.of(tacheAtraiter));
        Mockito.when(tacheAtraiteRepository.save(tacheAtraiter)).thenReturn(tacheAtraiter);

        ResponseEntity<Object> response = tacheAtraiteService.RejeterTache(id, tacheAtraiter);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        assertEquals("tacheAtraiter successfully updated!", responseBody.get("message"));
        assertEquals("traité", tacheAtraiter.getStatut());
        assertEquals("Rejeter", tacheAtraiter.getApprobation());
    }

    @Test
    public void testRejeterTacheNotFound() {
        Long id = 1L;
        TacheAtraiter tacheAtraiter = new TacheAtraiter();

        Mockito.when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> tacheAtraiteService.RejeterTache(id, tacheAtraiter));
    }
}