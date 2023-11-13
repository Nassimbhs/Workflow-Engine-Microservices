package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import workflow.example.workflow.duplication.TacheAtraiterCommon;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacheAtraiterDto implements TacheAtraiterCommon {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private String statut;
    private String action;
    private String approbation;
    private Long responsable;
    private String emailResponsable;
    private Long workflowId;

}
