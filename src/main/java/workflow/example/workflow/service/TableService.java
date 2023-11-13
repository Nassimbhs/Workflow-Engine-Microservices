package workflow.example.workflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final JdbcTemplate jdbcTemplate;

    public List<String> getTables(String sgbd) {
        String query;

        if (sgbd == null) {
            sgbd = "mysql";
        }

        if (sgbd.equalsIgnoreCase("mysql")) {
            query = "SHOW TABLES";
        } else if (sgbd.equalsIgnoreCase("postgres")) {
            query = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        } else if (sgbd.equalsIgnoreCase("oracle")) {
            query = "SELECT table_name FROM all_tables";
        } else {
            throw new IllegalArgumentException("Unsupported SGBD: " + sgbd);
        }

        return jdbcTemplate.queryForList(query, String.class);
    }



}