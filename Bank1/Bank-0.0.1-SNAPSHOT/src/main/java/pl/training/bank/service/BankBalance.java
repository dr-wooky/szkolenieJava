package pl.training.bank.service;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(fixedRate = 3000)
    public void printBalance() {
        System.out.println("Bilans: " + accounts.getBankBalance());
    }

}
