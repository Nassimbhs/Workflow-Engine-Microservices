package Workflow.example.Workflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import workflow.example.workflow.service.TableService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableServiceTest {

    @InjectMocks
    private TableService tableService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTablesForMySQL() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/database";
        String username = "user";
        String password = "password";
        String sgbd = "mysql";

        List<Map<String, Object>> rows = new ArrayList<>();
        Map<String, Object> row1 = new HashMap<>();
        row1.put("TABLE_NAME", "table1");
        Map<String, Object> row2 = new HashMap<>();
        row2.put("TABLE_NAME", "table2");
        rows.add(row1);
        rows.add(row2);

        Mockito.when(jdbcTemplate.queryForList(("SHOW TABLES"))).thenReturn(rows);

        List<String> expectedTables = new ArrayList<>();
        expectedTables.add("table1");
        expectedTables.add("table2");

        List<String> result = tableService.getTables(jdbcUrl, username, password, sgbd);

        assertEquals(expectedTables, result);
    }
}