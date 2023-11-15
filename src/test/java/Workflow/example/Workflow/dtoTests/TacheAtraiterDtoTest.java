package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.TacheAtraiterDto;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TacheAtraiterDtoTest {

    @InjectMocks
    private TacheAtraiterDto tacheAtraiterDto;

    @Test
    void testTacheAtraiterDto() {
        tacheAtraiterDto.setId(1L);
        tacheAtraiterDto.setName("TestTask");
        tacheAtraiterDto.setDescription("Test description");
        tacheAtraiterDto.setCreationDate(new Date());

        assertEquals(1L, tacheAtraiterDto.getId());
        assertEquals("TestTask", tacheAtraiterDto.getName());
        assertEquals("Test description", tacheAtraiterDto.getDescription());
        assertNotNull(tacheAtraiterDto.getCreationDate());
        assertNotNull(tacheAtraiterDto.getStatut());
        assertNotNull(tacheAtraiterDto.getResponsable());
        assertNotNull(tacheAtraiterDto.getStatut());

    }
}
