package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.Workflow;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TacheEntityTest {

    @Test
    void testTacheWorkflowRelationship() {
        Tache tache = new Tache();
        Workflow workflow = new Workflow();

        tache.setWorkflowTache(workflow);

        Assertions.assertEquals(workflow, tache.getWorkflowTache());
    }

    @Test
    void testTacheLienTachesRelationship() {
        Tache tache = new Tache();
        tache.setId(1L);

        LienTache lienTache1 = new LienTache();

        LienTache lienTache2 = new LienTache();

        List<LienTache> lienTaches = List.of(lienTache1, lienTache2);
        tache.setLienTaches(lienTaches);

        Assertions.assertEquals(2, tache.getLienTaches().size());
        Assertions.assertTrue(tache.getLienTaches().contains(lienTache1));
        Assertions.assertTrue(tache.getLienTaches().contains(lienTache2));
    }


}
