package pl.training.bank;

import java.math.BigDecimal;

public interface Bank {

    void payInCashToAccount(String toAccountNumber, BigDecimal amount) throws BankException;

    void payOutCashFromAccount(String fromAccountNumber, BigDecimal amount) throws BankException;

    void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws BankException;

}
