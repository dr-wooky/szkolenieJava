package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

public interface Clients {

    Client save(Client client);
    Client getById(Long id) throws EntityNotFoundException;

}
