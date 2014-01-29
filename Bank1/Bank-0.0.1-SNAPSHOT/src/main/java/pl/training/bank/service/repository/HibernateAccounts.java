package pl.training.bank.service.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;

@DAO(type = DAO.Type.HIBERNATE)
public class HibernateAccounts implements Accounts {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateAccounts(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account save(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.save(account);
        session.flush();
        session.refresh(account);
        return account;
    }

    @Override
    public Account getById(Long id) throws EntityNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Account account = (Account) session.byId(Client.class);
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Account account = (Account) session.getNamedQuery(Account.SELECT_BY_NUMBER)
                .setParameter("number", number)
                .uniqueResult();
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }
}
