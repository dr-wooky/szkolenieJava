package pl.training.bank;

import pl.training.bank.service.repository.Clients;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;

import java.math.BigDecimal;

public class BankImpl implements Bank{

    private Accounts accounts;
    private Clients clients;
    private AccountNumberGenerator accountNumberGenerator;

    public BankImpl(Accounts accounts, Clients clients, AccountNumberGenerator accountNumberGenerator) {
        this.accounts = accounts;
        this.clients = clients;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Override
    public void payInCashToAccount(String toAccountNumber, BigDecimal amount) throws BankException {
        Account account = accounts.getByNumber(toAccountNumber);
        account.payIn(amount);
    }

    @Override
    public void payOutCashFromAccount(String fromAccountNumber, BigDecimal amount) throws BankException {
        Account account = accounts.getByNumber(fromAccountNumber);
        account.payOut(amount);
    }

    @Override
    public void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws BankException {
        payOutCashFromAccount(fromAccountNumber, amount);
        try {
            payInCashToAccount(toAccountNumber, amount);
        } catch (BankException e) {
            payInCashToAccount(fromAccountNumber, amount);
            throw new BankException();
        }
    }

    @Override
    public Client addClient(Client client) {
        return clients.save(client);
    }

    @Override
    public Account createAccount() {
        Account account = new Account();
        account.setNumber(accountNumberGenerator.next());
        account.setBalance(BigDecimal.ZERO);
        accounts.save(account);
        return account;
    }

    @Override
    public void assignClientToAccount(Long clientId, Long accountId) throws BankException{
        Client client = clients.getById(clientId);
        Account account = accounts.getById(accountId);
        account.addClient(client);
    }
}
