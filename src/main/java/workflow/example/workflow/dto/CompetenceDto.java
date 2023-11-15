package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceDto implements Serializable {

    private Long id;
    private String nomCompetence;

}
