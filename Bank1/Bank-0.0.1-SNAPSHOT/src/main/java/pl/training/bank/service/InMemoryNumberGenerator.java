package pl.training.bank.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryNumberGenerator implements AccountNumberGenerator {

    private AtomicLong lastAccountNumber = new AtomicLong();

    @Override
    public String next() {
        return String.format("%026d", lastAccountNumber.incrementAndGet());
    }
}
