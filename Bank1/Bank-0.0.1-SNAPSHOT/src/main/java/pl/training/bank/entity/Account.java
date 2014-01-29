package pl.training.bank.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = Account.SELECT_BY_NUMBER, query = "select a from Account a where a.number = :number")
@Table(name = "accounts")
@Entity
public class Account {

    public static final String SELECT_BY_NUMBER = "selectByNumber";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String number;
    private BigDecimal balance;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accounts_clients",
        joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients = new ArrayList<Client>();

    public Account() {
    }

    public Account(String number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
            client.addAccout(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void payIn(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void payOut(BigDecimal amount) throws InsufficientFundsException {
        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new InsufficientFundsException();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (clients != null ? !clients.equals(account.clients) : account.clients != null) return false;
        if (number != null ? !number.equals(account.number) : account.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (clients != null ? clients.hashCode() : 0);
        return result;
    }
}
