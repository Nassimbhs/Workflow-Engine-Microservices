package workflow.example.workflow.entitiesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import workflow.example.workflow.entity.JsonData;
import workflow.example.workflow.entity.TacheAtraiter;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class JsonDataEntityTest {

    @Test
    void testJsonDataEqualsAndHashCode() {
        JsonData jsonData1 = new JsonData(1L, "{\"key\":\"value\"}", 123L, "Pending", new ArrayList<>());
        JsonData jsonData2 = new JsonData(1L, "{\"key\":\"value\"}", 123L, "Approved", new ArrayList<>());

        Assertions.assertEquals(jsonData1, jsonData2);
        Assertions.assertEquals(jsonData1.hashCode(), jsonData2.hashCode());
    }

    @Test
    void testJsonDataNotEquals() {
        JsonData jsonData1 = new JsonData(1L, "{\"key\":\"value\"}", 123L, "Pending", new ArrayList<>());
        JsonData jsonData2 = new JsonData(2L, "{\"key\":\"value2\"}", 456L, "Approved", new ArrayList<>());

        Assertions.assertNotEquals(jsonData1, jsonData2);
    }

    @Test
    void testJsonDataTachesAtraiterRelationship() {
        JsonData jsonData = new JsonData();
        TacheAtraiter tacheAtraiter = mock(TacheAtraiter.class);

        List<TacheAtraiter> tachesAtraiterList = new ArrayList<>();
        tachesAtraiterList.add(tacheAtraiter);

        jsonData.setTachesAtraiter(tachesAtraiterList);

        Assertions.assertEquals(tachesAtraiterList, jsonData.getTachesAtraiter());
    }
}
