package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.InteretDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InteretDtoTest {

    @InjectMocks
    private InteretDto interetDto;

    @Test
    void testInteretDto() {
        interetDto.setId(1L);
        interetDto.setNom("Programming");

        assertEquals(1L, interetDto.getId());
        assertEquals("Programming", interetDto.getNom());

    }
}
