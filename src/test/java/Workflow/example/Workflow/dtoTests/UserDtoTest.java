package workflow.example.workflow.dtoTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.dto.UserDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserDtoTest {

    @Test
    void testUserDto() {
        Long id = 1L;
        String username = "sampleUser";
        String email = "sample@example.com";
        String password = "samplePassword";

        UserDto userDto = new UserDto(id, username, email, password);

        assertEquals(id, userDto.getId());
        assertEquals(username, userDto.getUsername());
        assertEquals(email, userDto.getEmail());
        assertEquals(password, userDto.getPassword());
    }
}
