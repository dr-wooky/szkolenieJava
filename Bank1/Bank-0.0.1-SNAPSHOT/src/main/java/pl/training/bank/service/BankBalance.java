package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.training.bank.service.repository.Accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Component
public class BankBalance {

    private Accounts accounts;

    @Autowired
    public BankBalance(Accounts accounts) {
        this.accounts = accounts;
    }

    public void printBalance() {
        System.out.println("Bilans: " + accounts.getBankBalance());
    }

}
