package workflow.example.workflow.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Langue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String niveau;

    @ManyToOne
    private Cv cv;
}
