package workflow.example.workflow.controllerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.TacheAtraiterController;
import workflow.example.workflow.converter.TacheAtraiterConverter;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.service.TacheAtraiteService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TacheAtraiterControllerTest {

    @InjectMocks
    private TacheAtraiterController tacheAtraiterController;
    @Mock
    private TacheAtraiteService tacheAtraiteService;
    @Mock
    private TacheAtraiterConverter tacheAtraiterConverter;

    @Test
    void testFindByWorkflowId() {
        Long workflowId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();

        when(tacheAtraiteService.findByWorkflowId(workflowId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(new ArrayList<>())).thenReturn(tacheAtraiterDtos);

        List<TacheAtraiterDto> result = tacheAtraiterController.findByWorkflowId(workflowId);

        assertEquals(tacheAtraiterDtos, result);

        verify(tacheAtraiteService, times(1)).findByWorkflowId(workflowId);
        verify(tacheAtraiterConverter, times(1)).entityToDto(new ArrayList<>());
    }

    @Test
    void testGetTacheAtraiterByResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();
        when(tacheAtraiteService.getTacheAtraiterByResponsable(responsableId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(anyList())).thenReturn(tacheAtraiterDtos);

        ResponseEntity<List<TacheAtraiterDto>> result = tacheAtraiterController.getTacheAtraiterByResponsable(responsableId);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok(tacheAtraiterDtos), result);
        verify(tacheAtraiteService).getTacheAtraiterByResponsable(responsableId);
        verify(tacheAtraiterConverter).entityToDto(anyList());
    }

    @Test
    void testUpdateTache() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        when(tacheAtraiteService.marquerTacheCommeTraite(id, tacheAtraiterDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = tacheAtraiterController.updateTache(id, tacheAtraiterDto);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(tacheAtraiteService).marquerTacheCommeTraite(id, tacheAtraiterDto);
    }

    @Test
    void testRejeterTache() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        when(tacheAtraiteService.rejeterTache(id, tacheAtraiterDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Object> result = tacheAtraiterController.rejeterTache(id, tacheAtraiterDto);

        Assertions.assertNotNull(result);
        assertEquals(ResponseEntity.ok().build(), result);
        verify(tacheAtraiteService).rejeterTache(id, tacheAtraiterDto);
    }

    @Test
    void testFindAll() {
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();
        when(tacheAtraiteService.getAlltachesAtraiter()).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(anyList())).thenReturn(tacheAtraiterDtos);

        List<TacheAtraiterDto> result = tacheAtraiterController.findAll();

        Assertions.assertNotNull(result);
        assertEquals(tacheAtraiterDtos, result);
        verify(tacheAtraiteService).getAlltachesAtraiter();
        verify(tacheAtraiterConverter).entityToDto(anyList());
    }

    @Test
    void testFindTacheById() {
        Long id = 1L;
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        when(tacheAtraiteService.findtacheById(id)).thenReturn(new TacheAtraiter());
        when(tacheAtraiterConverter.entityToDto((TacheAtraiter) any())).thenReturn(tacheAtraiterDto);

        TacheAtraiterDto result = tacheAtraiterController.findTacheById(id);

        Assertions.assertNotNull(result);
        assertEquals(tacheAtraiterDto, result);
        verify(tacheAtraiteService).findtacheById(id);
        verify(tacheAtraiterConverter).entityToDto((TacheAtraiter) any());
    }

    @Test
    void testGetTachesNonTraiteesParResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();
        when(tacheAtraiteService.getTachesTraiteesParResponsable(responsableId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(anyList())).thenReturn(tacheAtraiterDtos);

        List<TacheAtraiterDto> result = tacheAtraiterController.getTachesNonTraiteesParResponsable(responsableId);

        Assertions.assertNotNull(result);
        assertEquals(tacheAtraiterDtos, result);
        verify(tacheAtraiteService).getTachesTraiteesParResponsable(responsableId);
        verify(tacheAtraiterConverter).entityToDto(anyList());
    }

    @Test
    void testGetTacheNontraiterByResponsable() {
        Long responsableId = 1L;
        List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();
        when(tacheAtraiteService.getTacheNontraiterByResponsable(responsableId)).thenReturn(new ArrayList<>());
        when(tacheAtraiterConverter.entityToDto(anyList())).thenReturn(tacheAtraiterDtos);

        List<TacheAtraiterDto> result = tacheAtraiterController.getTacheNontraiterByResponsable(responsableId);

        Assertions.assertNotNull(result);
        assertEquals(tacheAtraiterDtos, result);
        verify(tacheAtraiteService).getTacheNontraiterByResponsable(responsableId);
        verify(tacheAtraiterConverter).entityToDto(anyList());
    }

}
