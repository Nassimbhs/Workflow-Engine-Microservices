package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDataDto implements Serializable {

    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String data;
    private Long responsable;
    private String etat;

}
