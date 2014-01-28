package pl.training.bank.entity;

import pl.training.bank.BankException;

public class InsufficientFundsException extends BankException{

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
