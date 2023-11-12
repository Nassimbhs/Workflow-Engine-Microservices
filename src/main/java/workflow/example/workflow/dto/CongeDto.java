package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CongeDto implements Serializable {

    private Long id;
    private Date dateDeb;
    private Date dateFin;
    private String type;
    private String statut;
    private String commentaire;
    private String responsable;

}
