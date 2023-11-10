package workflow.example.workflow.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Interet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interet;

    @ManyToOne
    private Cv cv;

}
