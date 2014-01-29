package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;

import javax.sql.DataSource;

@Service
public class JpaAccountNumberGenerator implements AccountNumberGenerator {

    private static final long DEFAULT_ACCOUNT_NUMBER = 1;
    private  Accounts accounts;

    @Autowired
    public JpaAccountNumberGenerator(Accounts accounts) {
        this.accounts = accounts;
    }

    @Override
    public String next() {
        String maxNumber = accounts.getMaxNumber();
        Long lastId = maxNumber != null ? Long.parseLong(maxNumber) : DEFAULT_ACCOUNT_NUMBER;
        return String.format("%026d", ++lastId);
    }


}
