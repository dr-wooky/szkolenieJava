package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.mapper.ClientMapper;

import javax.sql.DataSource;

@Qualifier("jdbc")
@Repository
public class JdbcClients implements Clients{

    private static final String SQL_INSERT = "insert into clients values (null, :firstName, :lastName";
    private static final String SQL_UPDATE
        = "update clients set firstName = :firstName, lastName = :lastName where id = :id";
    private static final String SQL_GET_BY_ID = "select * from clients where id = :id";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcClients(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Client save(Client client) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(client);
        int result = jdbcTemplate.update(SQL_UPDATE, params);
        if (result == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQL_INSERT, params);
            client.setId(keyHolder.getKey().longValue());
        }
        return client;
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        return jdbcTemplate.query(SQL_GET_BY_ID,
                new MapSqlParameterSource("id", id),
                new ClientMapper());
    }
}
