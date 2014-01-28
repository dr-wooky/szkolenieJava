package pl.training.bank;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.Accounts;
import pl.training.bank.service.repository.Clients;

@Configuration
public class BeansConfig {

    @Bean
    public Bank bank(@Qualifier("jdbc") Accounts accounts, @Qualifier("jdbc") Clients clients, AccountNumberGenerator accountNumberGenerator) {
        return new BankImpl(accounts, clients, accountNumberGenerator);
    }

}
