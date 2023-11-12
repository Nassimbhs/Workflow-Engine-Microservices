package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto implements Serializable {
    private Long id;
    private String poste;
    private String employeur;
    private Date dateDeb;
    private Date dateFin;
    private String description;
}
