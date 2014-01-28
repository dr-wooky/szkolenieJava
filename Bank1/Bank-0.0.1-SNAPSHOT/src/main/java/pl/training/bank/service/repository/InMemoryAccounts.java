package pl.training.bank.service.repository;

import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@DAO
public class InMemoryAccounts implements Accounts {

    private AtomicLong nextId = new AtomicLong();
    private Map<Long, Account> accounts = new HashMap<Long, Account>();

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(nextId.incrementAndGet());
        }
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getById(Long id) throws EntityNotFoundException {
        if (accounts.containsKey(id)) {
            return accounts.get(id);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        for (Account account: accounts.values()) {
            if (account.getNumber().equals(number)) {
                return account;
            }
        }
        throw new EntityNotFoundException();
    }
}
