package workflow.example.workflow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import workflow.example.workflow.service.TableService;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TableService tableService;

    @Test
    void testGetTablesForMySQL() {
        when(jdbcTemplate.queryForList("SHOW TABLES", String.class)).thenReturn(Arrays.asList("table1", "table2"));

        List<String> result = tableService.getTables("mysql");

        Assertions.assertEquals(Arrays.asList("table1", "table2"), result);
    }

    @Test
    void testGetTablesForPostgres() {
        when(jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema='public'", String.class))
                .thenReturn(Arrays.asList("tableA", "tableB"));

        List<String> result = tableService.getTables("postgres");

        Assertions.assertEquals(Arrays.asList("tableA", "tableB"), result);
    }

    @Test
    void testGetTablesForOracle() {
        when(jdbcTemplate.queryForList("SELECT table_name FROM all_tables", String.class)).thenReturn(Arrays.asList("tableX", "tableY"));

        List<String> result = tableService.getTables("oracle");

        Assertions.assertEquals(Arrays.asList("tableX", "tableY"), result);
    }

    @Test
    void testGetTablesForUnsupportedSGBD() {
        assertThrows(IllegalArgumentException.class, () -> tableService.getTables("unsupported"));
    }
}
