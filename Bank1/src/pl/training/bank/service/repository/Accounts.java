package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

public interface Accounts {

    Account save(Account account);
    Account getById(Long id) throws EntityNotFoundException;
    Account getByNumber(String number) throws EntityNotFoundException;
}
