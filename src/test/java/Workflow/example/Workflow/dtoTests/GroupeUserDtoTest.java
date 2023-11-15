package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.GroupeUserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GroupeUserDtoTest {

    @InjectMocks
    private GroupeUserDto groupeUserDto;

    @Test
    void testGroupeUserDto() {
        groupeUserDto.setId(1L);
        groupeUserDto.setNom("Admins");
        groupeUserDto.setDescription("Administrator group");

        assertEquals(1L, groupeUserDto.getId());
        assertEquals("Admins", groupeUserDto.getNom());
        assertEquals("Administrator group", groupeUserDto.getDescription());

    }
}

