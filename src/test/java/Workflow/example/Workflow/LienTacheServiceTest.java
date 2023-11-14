package workflow.example.workflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.repository.LienTacheRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.service.LienTacheService;
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
    void testDeleteLinkById() {
        Long id = 1L;
        LienTache existingLienTache = new LienTache();

        when(lienTacheRepository.findById(id)).thenReturn(Optional.of(existingLienTache));

        lienTacheService.deleteLinkById(id);

        verify(lienTacheRepository).delete(existingLienTache);
    }

}