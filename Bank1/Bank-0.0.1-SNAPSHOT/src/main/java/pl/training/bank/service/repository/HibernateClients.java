package pl.training.bank.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Client;

@DAO(type = DAO.Type.HIBERNATE)
public class HibernateClients implements Clients {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateClients(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.save(client);
        session.flush();
        session.refresh(client);
        return client;
    }

    @Override
    public Client getById(Long id) throws EntityNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Client client = (Client) session.byId(Client.class);
        if (client == null) {
            throw new EntityNotFoundException();
        }
        return client;
    }
}
