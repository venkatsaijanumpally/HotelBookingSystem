package com.hotel.bookingservice.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Component
public class QueryRunner {
    public static JdbcTemplate jdbcTemplate;
    private static final String USER_INFO_TABLE = "user_info";
    private static final String[] USER_INFO_PARAMETERS = {"user_id", "name", "address"};
    private static final Logger logger = LoggerFactory.getLogger(QueryRunner.class);

    @Autowired
    public QueryRunner(JdbcTemplate jdbcTemplate) {
        QueryRunner.jdbcTemplate = jdbcTemplate;
    }

    public static void execute(String query) {
        jdbcTemplate.execute(query);
    }

    public static void createTable(String tableName, String[] parameters, String[] values) {
        if (parameters.length < 1) {
            throw new IllegalArgumentException("At least one parameter is required for insertion.");
        }

        // Create the SQL statement with dynamic parameters
        String placeholders = String.join(", ", Arrays.stream(parameters).map(p -> "?").toArray(String[]::new));
        String sql = "INSERT INTO " + tableName + " (" + String.join(", ", parameters) + ") VALUES (" + placeholders + ")";

        System.out.println(sql);
        jdbcTemplate.update(sql, (Object[]) values);
    }

    public static void insertRecord(String tableName, String... values) {
        if (values.length < 1) {
            throw new IllegalArgumentException("At least one value is required.");
        }

        // Create the SQL statement with dynamic parameters
        String placeholders = String.join(", ", java.util.Collections.nCopies(values.length, "?"));
        String sql = "INSERT INTO " + tableName + " VALUES (" + placeholders + ")";
        logger.info("Insert query: " + sql + " Values: "+ Arrays.toString(values));
        // Execute the update
        jdbcTemplate.update(sql, (Object[]) values);
    }

    public static void insertRecord(String tableName, String[] parameters, String[] values) {
        if (values.length != parameters.length || values.length < 1) {
            throw new IllegalArgumentException("Number of values and parameters must match, and at least one value is required.");
        }

        // Create the SQL statement with dynamic parameters
        String placeholders = String.join(", ", parameters);
        String valuesPlaceholders = String.join(", ", java.util.Collections.nCopies(values.length, "?"));
        String sql = "INSERT INTO " + tableName + " (" + placeholders + ") VALUES (" + valuesPlaceholders + ")";
        logger.info("Insert query: " + sql + " Values: " + Arrays.toString(values));

        // Execute the update
        jdbcTemplate.update(sql, (Object[]) values);
    }

    public static void deleteRecord(String tableName, String[] parameters, String[] values) {
        Objects.requireNonNull(parameters, "Parameters must not be null");
        Objects.requireNonNull(values, "Values must not be null");

        if (parameters.length != values.length) {
            throw new IllegalArgumentException("Parameters and values must have the same length");
        }

        if (recordExists(tableName, parameters, values)) {
            StringBuilder conditionBuilder = new StringBuilder();
            for (int i = 0; i < parameters.length; i++) {
                conditionBuilder.append(parameters[i]).append(" = ?");

                if (i < parameters.length - 1) {
                    conditionBuilder.append(" AND ");
                }
            }

            String deleteSql = "DELETE FROM " + tableName + " WHERE " + conditionBuilder.toString();
            logger.info("Executing SQL: {}", deleteSql);

            jdbcTemplate.update(deleteSql, (Object[]) values);
            logger.info("Record deleted successfully from table '{}'.", tableName);
        } else {
            logger.info("Record does not exist in table '{}'.", tableName);
        }
    }

    private static boolean recordExists(String tableName, String[] parameters, String[] values) {
        Objects.requireNonNull(parameters, "Parameters must not be null");
        Objects.requireNonNull(values, "Values must not be null");

        if (parameters.length != values.length) {
            throw new IllegalArgumentException("Parameters and values must have the same length");
        }

        StringBuilder conditionBuilder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            conditionBuilder.append(parameters[i]).append(" = ?");

            if (i < parameters.length - 1) {
                conditionBuilder.append(" AND ");
            }
        }

        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + conditionBuilder.toString();
        logger.info("Executing SQL: {}", sql);

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, (Object[]) values);
        return count != null && count > 0;
    }

    public static <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.queryForObject(sql, rowMapper, args);
    }

    public static <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }

    private static boolean tableExists(String tableName) {
        System.out.println("CHECK");
        String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?";
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, Integer.class, tableName) > 0;
    }

    public static boolean tableDoesNotExist(String table) {
        return !tableExists(table);
    }
}
