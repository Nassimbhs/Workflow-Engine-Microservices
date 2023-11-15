package workflow.example.workflow.converterTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.converter.TacheConverter;
import workflow.example.workflow.converter.WorkflowConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.dto.TacheAtraiterDto;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkflowConverterTest {

    @Mock
    private TacheConverter tacheConverter;

    @InjectMocks
    private WorkflowConverter workflowConverter;

    @Test
    void testEntityToDto() {
        Workflow workflow = createSampleWorkflow();
        when(tacheConverter.entityToDto(anyList())).thenReturn(Collections.emptyList());

        WorkflowDto dto = workflowConverter.entityToDto(workflow);

        assertEquals(workflow.getId(), dto.getId());
        assertEquals(workflow.getName(), dto.getName());

        verify(tacheConverter, times(1)).entityToDto(anyList());
    }

    @Test
    void testEntityToDtoList() {
        List<Workflow> workflows = Collections.singletonList(createSampleWorkflow());
        when(tacheConverter.entityToDto(anyList())).thenReturn(Collections.emptyList());

        List<WorkflowDto> dtoList = workflowConverter.entityToDto(workflows);

        assertEquals(workflows.size(), dtoList.size());

        verify(tacheConverter, times(1)).entityToDto(anyList());
    }

    private Workflow createSampleWorkflow() {
        return new Workflow();
    }

    private LienTacheDto createSampleLienTacheDto() {
        LienTacheDto lienTacheDto = new LienTacheDto();
        lienTacheDto.setId(1L);
        return lienTacheDto;
    }

    private TacheAtraiterDto createSampleTacheAtraiterDto() {
        TacheAtraiterDto tacheAtraiterDto = new TacheAtraiterDto();
        tacheAtraiterDto.setId(1L);
        return tacheAtraiterDto;
    }

}
