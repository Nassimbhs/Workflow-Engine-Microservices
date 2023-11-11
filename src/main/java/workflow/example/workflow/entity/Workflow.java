package workflow.example.workflow.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Workflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;
    private String etat;
    private String declencheur;
    private String webhookUrl;
    private String jdbcUrl;
    private String username;
    private String password;
    private String sgbd;
    private String tacheAecouter;
    private String evenement;

    @OneToMany(mappedBy = "workflowTache", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Tache> taches = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Workflow workflow = (Workflow) o;
        return getId() != null && Objects.equals(getId(), workflow.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}