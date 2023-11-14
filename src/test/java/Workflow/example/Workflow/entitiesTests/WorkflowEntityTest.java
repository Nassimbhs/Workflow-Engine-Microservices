package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.Workflow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class WorkflowEntityTest {
    @Test
    void testWorkflowConstructor() {
        Workflow workflow = new Workflow(1L, "WorkflowName", "Description", new Date(), new Date(),
                "InProgress", "Trigger", "Webhook", "JDBC", "Username", "Password",
                "SGBD", "Task", "Event", new ArrayList<>());

        Assertions.assertEquals("WorkflowName", workflow.getName());
        Assertions.assertEquals("Description", workflow.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Workflow workflow1 = new Workflow(1L, "WorkflowName", "Description", new Date(), new Date(),
                "InProgress", "Trigger", "Webhook", "JDBC", "Username", "Password",
                "SGBD", "Task", "Event", new ArrayList<>());

        Workflow workflow2 = new Workflow(1L, "WorkflowName2", "Description2", new Date(), new Date(),
                "InProgress", "Trigger", "Webhook", "JDBC", "Username", "Password",
                "SGBD", "Task", "Event", new ArrayList<>());

        Assertions.assertEquals(workflow1, workflow2);

        Assertions.assertEquals(workflow1.hashCode(), workflow2.hashCode());
    }

    @Test
    void testWorkflowTachesRelationship() {
        Workflow workflow = new Workflow();

        Tache tache1 = new Tache();
        tache1.setId(1L);
        tache1.setName("Tache 1");
        tache1.setDescription("Description 1");
        tache1.setCreationDate(new Date());

        Tache tache2 = new Tache();
        tache2.setId(2L);
        tache2.setName("Tache 2");
        tache2.setDescription("Description 2");
        tache2.setCreationDate(new Date());

        List<Tache> taches = List.of(tache1, tache2);
        workflow.setTaches(taches);

        Assertions.assertEquals(2, workflow.getTaches().size());
        Assertions.assertTrue(workflow.getTaches().contains(tache1));
        Assertions.assertTrue(workflow.getTaches().contains(tache2));
    }

    @Test
    void testWorkflowEqualsWithNullId() {
        Workflow workflow1 = new Workflow(null, "WorkflowName", "Description", new Date(), new Date(),
                "InProgress", "Trigger", "Webhook", "JDBC", "Username", "Password",
                "SGBD", "Task", "Event", new ArrayList<>());

        Workflow workflow2 = new Workflow(null, "WorkflowName2", "Description2", new Date(), new Date(),
                "InProgress", "Trigger", "Webhook", "JDBC", "Username", "Password",
                "SGBD", "Task", "Event", new ArrayList<>());

        Assertions.assertEquals(workflow1.hashCode(), workflow2.hashCode());
    }

}
