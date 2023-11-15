package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.LienTacheController;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.service.LienTacheService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LienTacheControllerTest {

    @InjectMocks
    private LienTacheController lienTacheController;
    @Mock
    private LienTacheService lienTacheService;
    @Mock
    private LienTacheConverter lienTacheConverter;

    @Test
    void testAddLink() {
        LienTacheDto lienTacheDto = new LienTacheDto();

        when(lienTacheService.addLink(lienTacheDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = lienTacheController.addlink(lienTacheDto);

        assertEquals(ResponseEntity.ok().build(), result);

        verify(lienTacheService, times(1)).addLink(lienTacheDto);
    }


    @Test
    void testFindByTacheIdWithTacheLiee() {
        Long tacheId = 1L;
        List<LienTacheDto> lienTacheDtos = new ArrayList<>();

        when(lienTacheService.findByTacheIdWithTacheLiee(tacheId)).thenReturn(new ArrayList<>());
        when(lienTacheConverter.entityToDto(new ArrayList<>())).thenReturn(lienTacheDtos);

        List<LienTacheDto> result = lienTacheController.findByTacheIdWithTacheLiee(tacheId);

        assertEquals(lienTacheDtos, result);

        verify(lienTacheService, times(1)).findByTacheIdWithTacheLiee(tacheId);
        verify(lienTacheConverter, times(1)).entityToDto(new ArrayList<>());
    }

}