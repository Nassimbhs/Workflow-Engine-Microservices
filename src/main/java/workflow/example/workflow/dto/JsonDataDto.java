package workflow.example.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDataDto {

    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String data;
    private Long responsable;
    private String etat;

}
