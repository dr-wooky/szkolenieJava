package pl.training.bank.service.repository;

import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryClients implements Clients {

    private AtomicLong nextId = new AtomicLong();
    private Map<Long, Client> clients = new HashMap<Long, Client>();

    @Override
    public Client save(Client client) {
        if (client.getId() == null) {
            client.setId(nextId.incrementAndGet());
        }
        clients.put(client.getId(), client);
        return client;
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        if (clients.containsKey(id)) {
            return clients.get(id);
        }
        throw new EntityNotFoundException();
    }
}
