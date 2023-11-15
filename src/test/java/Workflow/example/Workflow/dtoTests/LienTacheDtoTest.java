package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.LienTacheDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LienTacheDtoTest {

    @InjectMocks
    private LienTacheDto lienTacheDto;

    @Test
    void testLienTacheDto() {
        lienTacheDto.setId(1L);
        lienTacheDto.setSource("SourceTask");
        lienTacheDto.setTarget("TargetTask");
        lienTacheDto.setWorkflowId("Workflow123");

        assertEquals(1L, lienTacheDto.getId());
        assertEquals("SourceTask", lienTacheDto.getSource());
        assertEquals("TargetTask", lienTacheDto.getTarget());
        assertEquals("Workflow123", lienTacheDto.getWorkflowId());
    }
}
