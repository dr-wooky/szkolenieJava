package pl.training.bank.service;

import pl.training.bank.Bank;
import pl.training.bank.BankException;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.Clients;

public class CreateAccount implements Runnable {

    private Bank bank;
    private Client client;

    public CreateAccount(Bank bank, Client client) {
        this.bank = bank;
        this.client = client;
    }

    @Override
    public void run() {
        if (client.getAccounts().isEmpty()) {
            Account account = bank.createAccount();
            try {
                bank.assignClientToAccount(client.getId(), account.getId());
            } catch (BankException e) {
                e.printStackTrace();
            }
        }
    }
}
