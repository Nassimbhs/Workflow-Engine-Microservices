package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.CongeDto;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CongeDtoTest {

    @InjectMocks
    private CongeDto congeDto;

    @Test
    void testCongeDto() {
        congeDto.setId(1L);
        congeDto.setDateDeb(new Date());
        congeDto.setDateFin(new Date());
        congeDto.setType("Vacation");
        congeDto.setStatut("Approved");
        congeDto.setCommentaire("Enjoy your time off!");
        congeDto.setResponsable("Manager ABC");

        assertEquals(1L, congeDto.getId());
        assertNotNull(congeDto.getDateDeb());
        assertNotNull(congeDto.getDateFin());
        assertEquals("Vacation", congeDto.getType());
        assertEquals("Approved", congeDto.getStatut());
        assertEquals("Enjoy your time off!", congeDto.getCommentaire());
        assertEquals("Manager ABC", congeDto.getResponsable());

    }
}
