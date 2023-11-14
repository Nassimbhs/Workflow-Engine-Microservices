package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.entity.Tache;

@ExtendWith(MockitoExtension.class)
class LienTacheEntityTest {

    @Test
    void testLienTacheEqualsAndHashCode() {
        LienTache lienTache1 = new LienTache(1L, "source1", "target1", "workflowId1",
                "tacheSourceName1", "tacheTargetName1", "type1", new Tache());

        LienTache lienTache2 = new LienTache(1L, "source2", "target2", "workflowId2",
                "tacheSourceName2", "tacheTargetName2", "type2", new Tache());

        Assertions.assertEquals(lienTache1, lienTache2);
        Assertions.assertEquals(lienTache1.hashCode(), lienTache2.hashCode());
    }

    @Test
    void testLienTacheNotEquals() {
        LienTache lienTache1 = new LienTache(1L, "source1", "target1", "workflowId1",
                "tacheSourceName1", "tacheTargetName1", "type1", new Tache());

        LienTache lienTache2 = new LienTache(2L, "source2", "target2", "workflowId2",
                "tacheSourceName2", "tacheTargetName2", "type2", new Tache());

        Assertions.assertNotEquals(lienTache1, lienTache2);
    }

}
