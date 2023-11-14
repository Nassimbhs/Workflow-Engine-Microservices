package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.Competence;
import workflow.example.workflow.entity.Cv;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CvEntityTest {
    @Test
    void testCvEqualsAndHashCode() {
        Cv cv1 = new Cv(1L, "John", "Doe", "john.doe@example.com", "Software Engineer", 123456789L,
                "123 Main St", "City", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Cv cv2 = new Cv(1L, "Jane", "Doe", "jane.doe@example.com", "Data Scientist", 987654321L,
                "456 Oak St", "Town", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Assertions.assertEquals(cv1, cv2);
        Assertions.assertEquals(cv1.hashCode(), cv2.hashCode());
    }

    @Test
    void testCvNotEquals() {
        Cv cv1 = new Cv(1L, "John", "Doe", "john.doe@example.com", "Software Engineer", 123456789L,
                "123 Main St", "City", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Cv cv2 = new Cv(2L, "Jane", "Doe", "jane.doe@example.com", "Data Scientist", 987654321L,
                "456 Oak St", "Town", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Assertions.assertNotEquals(cv1, cv2);
    }

    @Test
    void testCvCompetencesRelationship() {
        Cv cv = new Cv();
        Competence competence1 = new Competence();
        Competence competence2 = new Competence();

        cv.getCompetences().add(competence1);
        cv.getCompetences().add(competence2);

        Assertions.assertEquals(2, cv.getCompetences().size());
        Assertions.assertTrue(cv.getCompetences().contains(competence1));
        Assertions.assertTrue(cv.getCompetences().contains(competence2));
    }

}
