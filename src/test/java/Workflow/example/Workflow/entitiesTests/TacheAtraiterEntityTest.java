package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.Conge;
import workflow.example.workflow.entity.Cv;
import workflow.example.workflow.entity.JsonData;
import workflow.example.workflow.entity.TacheAtraiter;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class TacheAtraiterEntityTest {
    @Test
    void testTacheAtraiterEqualsAndHashCode() {
        TacheAtraiter tacheAtraiter1 = new TacheAtraiter();

        TacheAtraiter tacheAtraiter2 = new TacheAtraiter();

        Assertions.assertEquals(tacheAtraiter1.hashCode(), tacheAtraiter2.hashCode());
    }

    @Test
    void testTacheAtraiterCongesRelationship() {
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        Conge conge1 = new Conge();
        Conge conge2 = new Conge();

        tacheAtraiter.getConges().add(conge1);
        tacheAtraiter.getConges().add(conge2);

        Assertions.assertEquals(2, tacheAtraiter.getConges().size());
        Assertions.assertTrue(tacheAtraiter.getConges().contains(conge1));
        Assertions.assertTrue(tacheAtraiter.getConges().contains(conge2));
    }

    @Test
    void testTacheAtraiterCvsRelationship() {
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        Cv cv1 = new Cv();
        Cv cv2 = new Cv();

        tacheAtraiter.getCvs().add(cv1);
        tacheAtraiter.getCvs().add(cv2);

        Assertions.assertEquals(2, tacheAtraiter.getCvs().size());
        Assertions.assertTrue(tacheAtraiter.getCvs().contains(cv1));
        Assertions.assertTrue(tacheAtraiter.getCvs().contains(cv2));
    }

    @Test
    void testTacheAtraiterJsonDatasRelationship() {
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        JsonData jsonData1 = new JsonData();
        JsonData jsonData2 = new JsonData();

        tacheAtraiter.getJsonDatas().add(jsonData1);
        tacheAtraiter.getJsonDatas().add(jsonData2);

        Assertions.assertEquals(2, tacheAtraiter.getJsonDatas().size());
        Assertions.assertTrue(tacheAtraiter.getJsonDatas().contains(jsonData1));
        Assertions.assertTrue(tacheAtraiter.getJsonDatas().contains(jsonData2));
    }

    @Test
    void testTacheAtraiterNotEquals() {
        TacheAtraiter tacheAtraiter1 = new TacheAtraiter();

        TacheAtraiter tacheAtraiter2 = new TacheAtraiter();

        Assertions.assertNotEquals(tacheAtraiter1, tacheAtraiter2);
    }

    @Test
    void testToString() {
        TacheAtraiter tacheAtraiter = TacheAtraiter.builder()
                .id(1L)
                .name("Task1")
                .description("Description for Task1")
                .creationDate(new Date())
                .startDate(new Date())
                .endDate(new Date())
                .statut("InProgress")
                .action("Approve")
                .approbation("Approved")
                .responsable(123L)
                .emailResponsable("responsible@example.com")
                .workflowId(456L)
                .build();

        Assertions.assertNotNull(tacheAtraiter.toString());
    }

    @Test
    void testEquals() {
        TacheAtraiter tache1 = TacheAtraiter.builder().id(1L).build();
        TacheAtraiter tache2 = TacheAtraiter.builder().id(1L).build();
        TacheAtraiter tache3 = TacheAtraiter.builder().id(2L).build();

        Assertions.assertEquals(tache1, tache2);
        Assertions.assertNotEquals(tache1, tache3);
    }

    @Test
    void testHashCode() {
        TacheAtraiter tacheAtraiter = TacheAtraiter.builder().id(1L).build();

        Assertions.assertEquals(tacheAtraiter.hashCode(), tacheAtraiter.getClass().hashCode());
    }

}
