package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Account;
import pl.training.bank.service.repository.mapper.AccountMapper;

import javax.sql.DataSource;


//@Qualifier("jdbc")
//@Repository
@DAO(type = DAO.Type.JDBC)
public class JdbcAccounts implements Accounts {

    private static final String SQL_INSERT = "insert into accounts values (null, :number, :balance)";
    private static final String SQL_UPDATE
            = "update accounts set number = :number, balance = :balance where id = :id";
    private static final String SQL_GET_BY_ID = "select * from accounts where id = :id";
    private static final String SQL_GET_BY_NUMBER = "select * from accounts where number = :number";


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccounts(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Account save(Account account) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(account);
        int result = jdbcTemplate.update(SQL_UPDATE, params);
        if (result == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQL_INSERT, params, keyHolder);
            Long id = keyHolder.getKey().longValue();
            account.setId(id);
        }
        return account;
    }

    @Override
    public Account getById(Long id) throws EntityNotFoundException {
        Account account = jdbcTemplate.query(SQL_GET_BY_ID,
                new MapSqlParameterSource("id", id),
                new AccountMapper());
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        Account account = jdbcTemplate.query(SQL_GET_BY_NUMBER,
                new MapSqlParameterSource("number", number),
                new AccountMapper());
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }
}
