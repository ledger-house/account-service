package com.ledgerhouse.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AccountServiceApplicationTests {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    void flywayAppliesBaselineMigration() {
        Integer migrationCount =
                jdbcTemplate.queryForObject(
                        "select count(*) from account.flyway_schema_history where version = '1' and"
                            + " success",
                        Integer.class);

        assertThat(migrationCount).isEqualTo(1);
    }
}
