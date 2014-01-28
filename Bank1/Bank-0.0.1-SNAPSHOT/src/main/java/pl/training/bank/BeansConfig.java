package pl.training.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;
import pl.training.bank.service.repository.Clients;

@Configuration
public class BeansConfig {

    @Bean
    public Bank bank(Accounts accounts, Clients clients, AccountNumberGenerator accountNumberGenerator) {
        return new BankImpl(accounts, clients, accountNumberGenerator);
    }

}
