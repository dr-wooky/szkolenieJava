package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import java.math.BigDecimal;

public interface Accounts {

    Account save(Account account);
    Account getById(Long id) throws EntityNotFoundException;
    Account getByNumber(String number) throws EntityNotFoundException;
    String getMaxNumber();
    BigDecimal getBankBalance();
}
