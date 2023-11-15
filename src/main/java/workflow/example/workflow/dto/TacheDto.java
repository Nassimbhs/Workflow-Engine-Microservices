package workflow.example.workflow.dto;

import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TacheDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private String statut;
    private String triggerType;
    private String action;
    private String approbation;
    private Long workflowId;

    private List<LienTacheDto> lienTacheDtos = new ArrayList<>();
    private List<TacheAtraiterDto> tacheAtraiterDtos = new ArrayList<>();

}
