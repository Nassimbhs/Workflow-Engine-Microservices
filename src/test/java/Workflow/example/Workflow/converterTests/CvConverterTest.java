package workflow.example.workflow.converterTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.controller.WorfklowController;
import workflow.example.workflow.converter.*;
import workflow.example.workflow.dto.CvDto;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.*;
import workflow.example.workflow.service.WorkflowService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CvConverterTest {

    @InjectMocks
    private CvConverter cvConverter;

    @Mock
    private FormationConverter formationConverter;

    @Mock
    private CompetenceConverter competenceConverter;

    @Mock
    private InteretConverter interetConverter;

    @Mock
    private LangueConverter langueConverter;

    @Mock
    private ExperienceConverter experienceConverter;
    @Mock
    private WorkflowService workflowService;
    @Mock
    private WorkflowConverter workflowConverter;
    @InjectMocks
    private WorfklowController workflowController;
    @Test
    void testEntityToDto() {
        Cv cv = createSampleCv();
        when(formationConverter.entityToDto(cv.getFormations())).thenReturn(Collections.emptyList());
        when(competenceConverter.entityToDto(cv.getCompetences())).thenReturn(Collections.emptyList());
        when(interetConverter.entityToDto(cv.getInterets())).thenReturn(Collections.emptyList());
        when(langueConverter.entityToDto(cv.getLangues())).thenReturn(Collections.emptyList());
        when(experienceConverter.entityToDto(cv.getExperiences())).thenReturn(Collections.emptyList());

        CvDto dto = cvConverter.entityToDto(cv);

        assertEquals(cv.getId(), dto.getId());
    }

    @Test
    void testEntityToDtoList() {
        List<Cv> cvList = Collections.singletonList(createSampleCv());
        when(formationConverter.entityToDto(cvList.get(0).getFormations())).thenReturn(Collections.emptyList());
        when(competenceConverter.entityToDto(cvList.get(0).getCompetences())).thenReturn(Collections.emptyList());
        when(interetConverter.entityToDto(cvList.get(0).getInterets())).thenReturn(Collections.emptyList());
        when(langueConverter.entityToDto(cvList.get(0).getLangues())).thenReturn(Collections.emptyList());
        when(experienceConverter.entityToDto(cvList.get(0).getExperiences())).thenReturn(Collections.emptyList());

        List<CvDto> dtoList = cvConverter.entityToDto(cvList);

        assertEquals(cvList.size(), dtoList.size());
    }

    private Cv createSampleCv() {
        Cv cv = new Cv();
        cv.setId(1L);
        cv.setFormations(Arrays.asList(new Formation(), new Formation()));
        cv.setCompetences(Arrays.asList(new Competence(), new Competence()));
        cv.setInterets(Arrays.asList(new Interet(), new Interet()));
        cv.setLangues(Arrays.asList(new Langue(), new Langue()));
        cv.setExperiences(Arrays.asList(new Experience(), new Experience()));
        return cv;
    }

    @Test
    void testFindEmployeeById() {
        // Arrange
        Long workflowId = 1L;
        WorkflowDto expectedDto = new WorkflowDto();
        when(workflowService.findWorkflowById(workflowId)).thenReturn(new Workflow());
        when(workflowConverter.entityToDto(any(Workflow.class))).thenReturn(expectedDto);

        // Act
        WorkflowDto result = workflowController.findEmployeeById(workflowId);

        // Assert
        assertEquals(expectedDto, result);
        verify(workflowService).findWorkflowById(workflowId);
        verify(workflowConverter).entityToDto(any(Workflow.class));
    }

    @Test
    void testFindAll() {
        List<Workflow> workflows = new ArrayList<>();
        when(workflowService.getAllWorkflows()).thenReturn(workflows);
        when(workflowConverter.entityToDto(workflows)).thenReturn(new ArrayList<>());

        List<WorkflowDto> result = workflowController.findAll();

        Assertions.assertNotNull(result);
        verify(workflowService).getAllWorkflows();
        verify(workflowConverter).entityToDto(workflows);
    }

    @Test
    void testDeleteWorkflow() {
        Long workflowId = 1L;

        workflowController.deleteWorkflow(workflowId);

        verify(workflowService).deleteWorkflowById(workflowId);
    }

    @Test
    void testUpdateWorkflow() {
        Long workflowId = 1L;
        WorkflowDto workflowDto = new WorkflowDto();
        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().build();
        when(workflowService.updateWorkflow(workflowId, workflowDto)).thenReturn(expectedResponse);

        ResponseEntity<Object> result = workflowController.updateWorkflow(workflowId, workflowDto);

        assertEquals(expectedResponse, result);
        verify(workflowService).updateWorkflow(workflowId, workflowDto);
    }

}
