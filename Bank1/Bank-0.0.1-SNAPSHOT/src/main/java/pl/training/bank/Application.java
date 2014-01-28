package pl.training.bank;

import pl.training.bank.service.InMemoryNumberGenerator;
import pl.training.bank.service.repository.Clients;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;
import pl.training.bank.service.repository.InMemoryAccounts;
import pl.training.bank.service.repository.InMemoryClients;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) {
        Accounts accounts = new InMemoryAccounts();
        Clients clients = new InMemoryClients();
        AccountNumberGenerator accountNumberGenerator = new InMemoryNumberGenerator();

        Bank bank = new BankImpl(accounts, clients, accountNumberGenerator);

        Account account1 = new Account();
        account1.setBalance(new BigDecimal(1000000));
        account1 = bank.addAccount(account1);

        Client client1 = new Client();
        client1.setFirstName("Ania");
        client1.setLastName("May");
        bank.addClient(client1);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal(0));
        account2 = bank.addAccount(account2);

        try {
            bank.transferCash(account1.getNumber(), account2.getNumber(), new BigDecimal(10000));
        } catch (BankException e) {
            System.out.println(e);
        }

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

    }
}
