package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityNotFoundException;

public interface AccountsCustom {

    Account getByNumber(String number) throws EntityNotFoundException;
}
