package com.hotel.bookingservice.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetupManager {
    private static JdbcTemplate jdbcTemplate;
    private static final String USER_INFO_TABLE = "user_info";
    @Autowired
    public DatabaseSetupManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void createUserInfoTable() {
        String sql = "CREATE TABLE " + USER_INFO_TABLE + " (" +
                "user_id VARCHAR(255) PRIMARY KEY, " +
                "address VARCHAR(255), " +
                "name VARCHAR(255))";
        QueryRunner.execute(sql);
    }

    public static void createUserInfoTableIfNotExist() {
        if(QueryRunner.tableDoesNotExist(USER_INFO_TABLE))
            createUserInfoTable();
    }
}
