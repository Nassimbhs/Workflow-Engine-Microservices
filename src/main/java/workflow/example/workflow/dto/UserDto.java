package workflow.example.workflow.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String password;

}