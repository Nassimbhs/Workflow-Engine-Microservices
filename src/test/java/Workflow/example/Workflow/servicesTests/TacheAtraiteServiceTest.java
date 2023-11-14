package workflow.example.workflow.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.converter.TacheAtraiterConverter;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.service.TacheAtraiteService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TacheAtraiteServiceTest {

    @Mock
    private TacheAtraiteRepository tacheAtraiteRepository;

    @Mock
    private TacheAtraiterConverter tacheAtraiterConverter;

    @InjectMocks
    private TacheAtraiteService tacheAtraiteService;

    @Test
    void testGetTacheAtraiterByResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiter> expectedTaches = Collections.singletonList(new TacheAtraiter());

        when(tacheAtraiteRepository.findByResponsable(responsableId)).thenReturn(expectedTaches);

        List<TacheAtraiter> actualTaches = tacheAtraiteService.getTacheAtraiterByResponsable(responsableId);

        assertEquals(expectedTaches, actualTaches);
    }

    @Test
    void testMarquerTacheCommeTraite() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        TacheAtraiter tacheAtraiter = new TacheAtraiter();

        when(tacheAtraiterConverter.dtoToEntity(tacheAtraiterDto)).thenReturn(tacheAtraiter);
        when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.of(new TacheAtraiter()));

        ResponseEntity<Object> response = tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiterDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testMarquerTacheCommeTraiteTaskNotFound() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();

        when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiterDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testRejeterTache() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        TacheAtraiter tacheAtraiter = new TacheAtraiter();

        when(tacheAtraiterConverter.dtoToEntity(tacheAtraiterDto)).thenReturn(tacheAtraiter);
        when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.of(new TacheAtraiter()));

        ResponseEntity<Object> response = tacheAtraiteService.rejeterTache(id, tacheAtraiterDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testRejeterTacheTaskNotFound() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();

        when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tacheAtraiteService.rejeterTache(id, tacheAtraiterDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testGetAlltachesAtraiter() {
        List<TacheAtraiter> expectedTaches = Collections.singletonList(new TacheAtraiter());

        when(tacheAtraiteRepository.findAll()).thenReturn(expectedTaches);

        List<TacheAtraiter> actualTaches = tacheAtraiteService.getAlltachesAtraiter();

        assertEquals(expectedTaches, actualTaches);
    }

    @Test
    void testFindtacheById() {
        Long id = 1L;
        TacheAtraiter expectedTache = new TacheAtraiter();

        when(tacheAtraiteRepository.findById(id)).thenReturn(Optional.of(expectedTache));

        TacheAtraiter actualTache = tacheAtraiteService.findtacheById(id);

        assertEquals(expectedTache, actualTache);
    }

    @Test
    void testGetTachesTraiteesParResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiter> expectedTaches = Collections.singletonList(new TacheAtraiter());

        when(tacheAtraiteRepository.getTachesTraiteesParResponsable(responsableId)).thenReturn(expectedTaches);

        List<TacheAtraiter> actualTaches = tacheAtraiteService.getTachesTraiteesParResponsable(responsableId);

        assertEquals(expectedTaches, actualTaches);
    }

    @Test
    void testFindByWorkflowId() {
        Long workflowId = 1L;
        List<TacheAtraiter> expectedTaches = Collections.singletonList(new TacheAtraiter());

        when(tacheAtraiteRepository.findByWorkflowId(workflowId)).thenReturn(expectedTaches);

        List<TacheAtraiter> actualTaches = tacheAtraiteService.findByWorkflowId(workflowId);

        assertEquals(expectedTaches, actualTaches);

        when(tacheAtraiteRepository.findByWorkflowId(workflowId)).thenReturn(Collections.emptyList());

        List<TacheAtraiter> emptyList = tacheAtraiteService.findByWorkflowId(workflowId);

        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testGetTacheNontraiterByResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiter> expectedTaches = Collections.singletonList(new TacheAtraiter());

        when(tacheAtraiteRepository.getTacheNontraiterByResponsable(responsableId)).thenReturn(expectedTaches);

        List<TacheAtraiter> actualTaches = tacheAtraiteService.getTacheNontraiterByResponsable(responsableId);

        assertEquals(expectedTaches, actualTaches);
    }

}
