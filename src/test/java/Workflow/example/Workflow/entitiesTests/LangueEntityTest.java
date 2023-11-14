package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.Cv;
import workflow.example.workflow.entity.Langue;

@ExtendWith(MockitoExtension.class)
class LangueEntityTest {

    @Test
    void testLangueEqualsAndHashCode() {
        Langue langue1 = new Langue(1L, "French", "Intermediate", new Cv());
        Langue langue2 = new Langue(1L, "Spanish", "Advanced", new Cv());

        Assertions.assertEquals(langue1, langue2);
        Assertions.assertEquals(langue1.hashCode(), langue2.hashCode());
    }

    @Test
    void testLangueNotEquals() {
        Langue langue1 = new Langue(1L, "French", "Intermediate", new Cv());
        Langue langue2 = new Langue(2L, "Spanish", "Advanced", new Cv());

        Assertions.assertNotEquals(langue1, langue2);
    }

    @Test
    void testLangueCvRelationship() {
        Langue langue = new Langue();
        Cv cv = new Cv();

        langue.setCv(cv);

        Assertions.assertEquals(cv, langue.getCv());
    }
}
