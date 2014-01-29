package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.training.bank.entity.Account;

@Repository
public class AccountsImpl implements Accounts {

    private AccountsJpa accounts;

    @Autowired
    public AccountsImpl(AccountsJpa accounts) {
        this.accounts = accounts;
    }

    @Override
    public Account save(Account account) {
        accounts.save(account);
        return account;
    }

    @Override
    public Account getById(Long id) throws EntityNotFoundException {
        return accounts.findOne(id);
    }

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        Account account = accounts.getByNumber(number);
        if (account == null) {
            throw new EntityNotFoundException();
        }
        return account;
    }

    @Override
    public String getMaxNumber() {
        return accounts.getMaxNumber();
    }
}
