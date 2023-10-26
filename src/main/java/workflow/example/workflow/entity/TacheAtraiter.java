package workflow.example.workflow.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import workflow.example.workflow.listener.TacheAtraiterListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(TacheAtraiterListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TacheAtraiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    private Tache tacheAtraite;

    @OneToMany(mappedBy = "tacheAtraiter", cascade = CascadeType.ALL)
    List<Conge> conges = new ArrayList<>();

    @ManyToMany(mappedBy = "tachesAtraiter")
    private List<Cv> cvs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tache_atraiter_jsondata",
            joinColumns = @JoinColumn(name = "tache_atraiter_id"),
            inverseJoinColumns = @JoinColumn(name = "jsondata_id")
    )
    private List<JsonData> jsonDatas = new ArrayList<>();

}