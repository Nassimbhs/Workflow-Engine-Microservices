package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.ExperienceDto;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ExperienceDtoTest {

    @InjectMocks
    private ExperienceDto experienceDto;

    @Test
    void testExperienceDto() {
        experienceDto.setId(1L);
        experienceDto.setPoste("Software Engineer");
        experienceDto.setEmployeur("Tech Company XYZ");
        experienceDto.setDateDeb(new Date());
        experienceDto.setDateFin(new Date());
        experienceDto.setDescription("Worked on developing new features");

        assertEquals(1L, experienceDto.getId());
        assertEquals("Software Engineer", experienceDto.getPoste());
        assertEquals("Tech Company XYZ", experienceDto.getEmployeur());
        assertNotNull(experienceDto.getDateDeb());
        assertNotNull(experienceDto.getDateFin());
        assertEquals("Worked on developing new features", experienceDto.getDescription());

    }
}
