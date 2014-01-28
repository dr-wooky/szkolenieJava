package pl.training.bank.service.repository;

import pl.training.bank.BankException;

public class EntityNotFoundException extends BankException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
