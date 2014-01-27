package pl.training.bank.service;

import java.util.concurrent.atomic.AtomicLong;

public class InMemoryNumberGenerator implements AccountNumberGenerator {

    private AtomicLong lastAccountNumber = new AtomicLong();

    @Override
    public String next() {
        return String.format("%026d", lastAccountNumber.incrementAndGet());
    }
}
