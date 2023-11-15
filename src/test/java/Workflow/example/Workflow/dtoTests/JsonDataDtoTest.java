package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.JsonDataDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JsonDataDtoTest {

    @InjectMocks
    private JsonDataDto jsonDataDto;

    @Test
    void testJsonDataDto() {
        jsonDataDto.setId(1L);
        jsonDataDto.setResponsable(100L);
        jsonDataDto.setEtat("Processed");

        assertEquals(1L, jsonDataDto.getId());
        assertEquals(100L, jsonDataDto.getResponsable());
        assertEquals("Processed", jsonDataDto.getEtat());

    }
}

