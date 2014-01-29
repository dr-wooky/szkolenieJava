package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DAO(type = DAO.Type.JPA)
public class JpaClients implements Clients {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Client save(Client client) {
        entityManager.persist(client);
        entityManager.flush();
        entityManager.refresh(client);
        return client;
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        Client client = entityManager.find(Client.class, id);
        if (client == null) {
            throw new EntityNotFoundException();
        }
        return client;
    }
}
