package pl.training.bank.service.repository.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import pl.training.bank.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements ResultSetExtractor<Account> {

    @Override
    public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Account account = null;
        if (resultSet.next()) {
            account = new Account(resultSet.getString("number"), resultSet.getBigDecimal("balance"));
            account.setId(resultSet.getLong("id"));
        }
        return account;
    }
}
