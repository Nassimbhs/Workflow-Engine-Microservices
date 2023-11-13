package workflow.example.workflow.duplication;

import java.io.Serializable;
import java.util.Date;

public interface TacheAtraiterCommon extends Serializable {
    Long getId();
    String getName();
    String getDescription();
    Date getCreationDate();
    Date getStartDate();
    Date getEndDate();
    String getStatut();
    String getAction();
    String getApprobation();
    Long getResponsable();
    String getEmailResponsable();
    Long getWorkflowId();
}
