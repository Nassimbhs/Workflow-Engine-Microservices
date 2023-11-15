package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.TacheDto;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class TacheDtoTest {
    @InjectMocks
    private TacheDto tacheDto;

    @Test
    void testTacheDto() {
        tacheDto.setId(1L);
        tacheDto.setName("TestTask");
        tacheDto.setDescription("Test description");
        tacheDto.setCreationDate(new Date());
        tacheDto.setAction("");
        tacheDto.setStatut("");
        tacheDto.setTriggerType("");

        Assertions.assertEquals("TestTask", tacheDto.getName());
        Assertions.assertEquals("Test description", tacheDto.getDescription());
        Assertions.assertNotNull(tacheDto.getCreationDate());
        Assertions.assertNotNull(tacheDto.getId());
        Assertions.assertNotNull(tacheDto.getName());
        Assertions.assertNotNull(tacheDto.getDescription());
        Assertions.assertNotNull(tacheDto.getAction());
        Assertions.assertNotNull(tacheDto.getStatut());
        Assertions.assertNotNull(tacheDto.getTriggerType());
    }

}
