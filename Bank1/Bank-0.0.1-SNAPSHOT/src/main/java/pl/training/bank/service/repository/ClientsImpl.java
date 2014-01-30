package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.training.bank.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientsImpl implements Clients {

    private ClientsJpa clients;

    @Autowired
    public ClientsImpl(ClientsJpa clients) {
        this.clients = clients;
    }

    @Override
    public Client save(Client client) {
        clients.save(client);
        return client;
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        Client client = clients.findOne(id);
        if (client == null) {
            throw new EntityNotFoundException();
        }
        return client;
    }

    public List<Client> findAll() {
        return clients.findAll();
    }
}
