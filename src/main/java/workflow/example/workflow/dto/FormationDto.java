package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationDto implements Serializable {
    private Long id;
    private String nomFormation;
    private String etablissement;
    private Date dateDeb;
    private Date dateFin;
}
