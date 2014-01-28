package pl.training.bank;

import pl.training.bank.entity.Client;
import pl.training.bank.entity.Account;

import java.math.BigDecimal;

public interface Bank {

    void payInCashToAccount(String toAccountNumber, BigDecimal amount) throws BankException;

    void payOutCashFromAccount(String fromAccountNumber, BigDecimal amount) throws BankException;

    void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws BankException;

    Client addClient(Client client);

    Account createAccount();

    void assignClientToAccount(Long clientId, Long accountId) throws BankException;

    BigDecimal getBalance(String accountNumber) throws BankException;

}
