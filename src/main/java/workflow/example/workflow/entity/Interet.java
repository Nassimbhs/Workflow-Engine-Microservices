package workflow.example.workflow.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Interet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interet;

    @ManyToOne
    private Cv cv;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var interet = (Interet) o;
        return getId() != null && Objects.equals(getId(), interet.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
