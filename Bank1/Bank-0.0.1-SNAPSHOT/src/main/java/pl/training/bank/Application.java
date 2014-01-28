package pl.training.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) throws BankException{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bank.xml", "bank-repository.xml");
        Bank bank = applicationContext.getBean(Bank.class);

        Account account1 = bank.createAccount();
        bank.payInCashToAccount(account1.getNumber(), new BigDecimal(10002));

        Client client1 = new Client();
        client1.setFirstName("Ania");
        client1.setLastName("May");
        bank.addClient(client1);

        Account account2 = bank.createAccount();

        try {
            bank.transferCash(account1.getNumber(), account2.getNumber(), new BigDecimal(10000));
        } catch (BankException e) {
            System.out.println(e);
        }

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

    }
}
