package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.FormationDto;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FormationDtoTest {

    @InjectMocks
    private FormationDto formationDto;

    @Test
    void testFormationDto() {
        formationDto.setId(1L);
        formationDto.setNomFormation("Computer Science");
        formationDto.setEtablissement("University XYZ");
        formationDto.setDateDeb(new Date());
        formationDto.setDateFin(new Date());

        assertEquals(1L, formationDto.getId());
        assertEquals("Computer Science", formationDto.getNomFormation());
        assertEquals("University XYZ", formationDto.getEtablissement());
        assertNotNull(formationDto.getDateDeb());
        assertNotNull(formationDto.getDateFin());

    }
}
