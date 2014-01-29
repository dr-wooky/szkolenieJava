package pl.training.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.bank.service.repository.Clients;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;

import java.math.BigDecimal;

@Component
public class BankImpl implements Bank{

    private Accounts accounts;
    private Clients clients;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public BankImpl(
            Accounts accounts,
            Clients clients,
            AccountNumberGenerator accountNumberGenerator) {
        this.accounts = accounts;
        this.clients = clients;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Override
    public void payInCashToAccount(String toAccountNumber, BigDecimal amount) throws BankException {
        Account account = accounts.getByNumber(toAccountNumber);
        account.payIn(amount);
        accounts.save(account);
    }

    @Override
    public void payOutCashFromAccount(String fromAccountNumber, BigDecimal amount) throws BankException {
        Account account = accounts.getByNumber(fromAccountNumber);
        account.payOut(amount);
        accounts.save(account);
    }

    @Override
    public void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws BankException {
        payOutCashFromAccount(fromAccountNumber, amount);
//      try {
            payInCashToAccount(toAccountNumber, amount);
//        } catch (BankException e) {
//            payInCashToAccount(fromAccountNumber, amount);
//            throw new BankException();
//        }
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
        Client client = clients.findOne(clientId);
        Account account = accounts.findOne(accountId);
        account.addClient(client);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) throws BankException{
        Account account = accounts.getByNumber(accountNumber);
        return account.getBalance();
    }
}
