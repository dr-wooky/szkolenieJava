package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import pl.training.bank.service.AccountNumberGenerator;

import javax.sql.DataSource;

@DAO(type = DAO.Type.JDBC)
public class JdbcAccountNumberGenerator implements AccountNumberGenerator {

    private static final long DEFAULT_ACCOUNT_NUMBER = 1;
    private static final String SQL_LAST_ACCOUNT_NUMBER =
            "select max(number) from accounts";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountNumberGenerator(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public String next() {
        Long lastId = jdbcTemplate.queryForObject(SQL_LAST_ACCOUNT_NUMBER,
                new MapSqlParameterSource(),
                Long.class);
        lastId = lastId != null ? lastId : DEFAULT_ACCOUNT_NUMBER;
        return String.format("%026d", ++lastId);
    }


}
