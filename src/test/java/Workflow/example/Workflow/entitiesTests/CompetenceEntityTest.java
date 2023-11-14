package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.Competence;
import workflow.example.workflow.entity.Cv;

@ExtendWith(MockitoExtension.class)
class CompetenceEntityTest {

    @Test
    void testCompetenceEqualsAndHashCode() {
        Competence competence1 = new Competence(1L, "Java Programming", new Cv());
        Competence competence2 = new Competence(1L, "Python Programming", new Cv());

        Assertions.assertEquals(competence1, competence2);
        Assertions.assertEquals(competence1.hashCode(), competence2.hashCode());
    }

    @Test
    void testCompetenceNotEquals() {
        Competence competence1 = new Competence(1L, "Java Programming", new Cv());
        Competence competence2 = new Competence(2L, "Python Programming", new Cv());

        Assertions.assertNotEquals(competence1, competence2);
    }

    @Test
    void testCompetenceCvRelationship() {
        Competence competence = new Competence();
        Cv cv = new Cv();

        competence.setCv(cv);

        Assertions.assertEquals(cv, competence.getCv());
    }

}
