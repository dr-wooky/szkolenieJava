package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@DAO(type = DAO.Type.JPA)
public class JpaAccounts implements Accounts {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public Account getById(Long id) throws EntityNotFoundException {
        Account account = entityManager.find(Account.class, id);
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        Account account;
        try {
            account = entityManager
                .createNamedQuery(Account.SELECT_BY_NUMBER, Account.class)
                .setParameter("number", number)
                .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException();
        }
        return account;
    }
}
