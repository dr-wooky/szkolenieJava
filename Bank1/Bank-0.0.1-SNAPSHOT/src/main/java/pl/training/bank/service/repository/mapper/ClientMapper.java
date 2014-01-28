package pl.training.bank.service.repository.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import pl.training.bank.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements ResultSetExtractor<Client> {

    @Override
    public Client extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Client client = null;
        if (resultSet.next()) {
            client = new Client(resultSet.getString("firstName"), resultSet.getString("lastName"));
            client.setId(resultSet.getLong("id"));
        }
        return client;
    }
}
