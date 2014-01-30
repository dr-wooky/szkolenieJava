package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

import java.util.List;

public interface Clients {

    Client save(Client client);
    Client getById(Long id) throws EntityNotFoundException;
    List<Client> findAll();
    public void delete(Long id);

}
