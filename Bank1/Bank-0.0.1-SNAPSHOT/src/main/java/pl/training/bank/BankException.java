package pl.training.bank;

public class BankException extends Exception {

    public BankException() {
    }

    public BankException(Throwable cause) {
        super(cause);
    }

    public BankException(String message) {
        super(message);
    }
}
