package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.LienTacheController;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.service.LienTacheService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
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

    @Test
    void testDeleteLink() {
        Long id = 1L;

        lienTacheController.deleteLink(id);

        verify(lienTacheService).deleteLinkById(id);
    }

    @Test
    void testFindAll() {
        List<LienTacheDto> lienTacheDtos = new ArrayList<>();
        when(lienTacheService.getAllLinks()).thenReturn(new ArrayList<>());
        when(lienTacheConverter.entityToDto(anyList())).thenReturn(lienTacheDtos);

        List<LienTacheDto> result = lienTacheController.findAll();

        Assertions.assertNotNull(result);
        assertEquals(lienTacheDtos, result);
        verify(lienTacheService).getAllLinks();
        verify(lienTacheConverter).entityToDto(anyList());
    }

    @Test
    void testFindLinkById() {
        Long id = 1L;
        LienTacheDto lienTacheDto = new LienTacheDto();
        when(lienTacheService.findLinkById(id)).thenReturn(new LienTache());
        when(lienTacheConverter.entityToDto((LienTache) any())).thenReturn(lienTacheDto);

        LienTacheDto result = lienTacheController.findLinkById(id);

        Assertions.assertNotNull(result);
        assertEquals(lienTacheDto, result);
        verify(lienTacheService).findLinkById(id);
        verify(lienTacheConverter).entityToDto((LienTache) any());
    }

    @Test
    void testFindByWorkflowId() {
        String workflowId = "1";
        List<LienTacheDto> lienTacheDtos = new ArrayList<>();
        when(lienTacheService.findByWorkflowId(workflowId)).thenReturn(new ArrayList<>());
        when(lienTacheConverter.entityToDto(anyList())).thenReturn(lienTacheDtos);

        List<LienTacheDto> result = lienTacheController.findByWorkflowId(workflowId);

        Assertions.assertNotNull(result);
        assertEquals(lienTacheDtos, result);
        verify(lienTacheService).findByWorkflowId(workflowId);
        verify(lienTacheConverter).entityToDto(anyList());
    }

}