package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityNotFoundException;

public class AccountsImpl extends AccountsCustom {

    @Override
    public Account getByNumber(String number) throws EntityNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
