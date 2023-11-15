package workflow.example.workflow.servicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.repository.LienTacheRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.service.LienTacheService;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LienTacheServiceTest {

    @Mock
    private LienTacheRepository lienTacheRepository;
    @Mock
    private TacheRepository tacheRepository;
    @Mock
    private LienTacheConverter lienTacheConverter;
    @InjectMocks
    private LienTacheService lienTacheService;

    @Test
    void testUpdateLink() {
        Long id = 1L;
        LienTacheDto lienTacheDto = new LienTacheDto();
        LienTache existingLienTache = new LienTache();

        when(lienTacheRepository.findById(id)).thenReturn(Optional.of(existingLienTache));
        when(lienTacheConverter.dtoToEntity(lienTacheDto)).thenReturn(existingLienTache);

        ResponseEntity<Object> response = lienTacheService.updateLink(id, lienTacheDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testAddLink_Success() {
        Long tacheId = 1L;

        LienTacheDto lienTacheDto = new LienTacheDto();

        LienTache lienTacheEntity = new LienTache();

        Tache tache = new Tache();
        tache.setId(tacheId);

        when(lienTacheConverter.dtoToEntity(lienTacheDto)).thenReturn(lienTacheEntity);
        when(tacheRepository.findById(any())).thenReturn(Optional.of(tache));
        when(lienTacheRepository.save(any(LienTache.class))).thenReturn(lienTacheEntity);

        ResponseEntity<Object> response = lienTacheService.addLink(lienTacheDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteLinkById() {
        Long id = 1L;
        LienTache existingLienTache = new LienTache();

        when(lienTacheRepository.findById(id)).thenReturn(Optional.of(existingLienTache));

        lienTacheService.deleteLinkById(id);

        verify(lienTacheRepository).delete(existingLienTache);
    }

    @Test
    void testGetAllLinks() {
        when(lienTacheRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), lienTacheService.getAllLinks());
    }

    @Test
    void testFindByTacheIdWithTacheLiee() {
        Long activiteId = 1L;
        when(lienTacheRepository.findByTacheIdWithTacheLiee(activiteId)).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), lienTacheService.findByTacheIdWithTacheLiee(activiteId));
    }

    @Test
    void testFindByWorkflowId() {
        String workflowId = "workflowId";
        when(lienTacheRepository.findByWorkflowId(workflowId)).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), lienTacheService.findByWorkflowId(workflowId));
    }

    @Test
    void testFindLinkById_LinkNotFound() {
        Long linkId = 1L;

        when(lienTacheRepository.findById(linkId)).thenReturn(Optional.empty());

        try {
            LienTache result = lienTacheService.findLinkById(linkId);
            Assertions.fail("Expected ResponseStatusException, but got result: " + result);
        } catch (ResponseStatusException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
            assertEquals("Link not found", e.getReason());
        }
    }

    @Test
    void testFindLinkById_LinkFound() {
        Long linkId = 1L;
        LienTache expectedLink = new LienTache();

        when(lienTacheRepository.findById(linkId)).thenReturn(Optional.of(expectedLink));

        LienTache result = lienTacheService.findLinkById(linkId);

        assertEquals(expectedLink, result);
    }

    @Test
    void testDeleteLinkById_LinkNotFound() {
        long linkId = 1L;
        when(lienTacheRepository.findById(linkId)).thenReturn(Optional.empty());

        try {
            lienTacheService.deleteLinkById(linkId);
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertEquals("Link not found !", ex.getReason());
        }

        verify(lienTacheRepository, times(1)).findById(linkId);
        verify(lienTacheRepository, never()).delete(any());
    }

}