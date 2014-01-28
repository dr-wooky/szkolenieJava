package pl.training.bank.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pl.training.bank.BankException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Aspect
@Service
public class CashFlowsLogger {

    private static final String ENTRY_SEPARATOR = "--------------------------------------------------------------------";

    private MessageSource messageSource;

    @Autowired
    public CashFlowsLogger(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Pointcut("execution(* pl.training.bank.Bank.*Cash*(..)" )
    public void financialOperation() {
    }

    private void log(String entry) {
        System.out.println(entry);
    }

    private String get(String key, Object ... params) {
        return  messageSource.getMessage(key, params, Locale.getDefault());
    }

    private void startLogEntry(String operationName) {
        log(ENTRY_SEPARATOR);
        log(get("operationStarted") + get(operationName));
    }

    private String formatCurrency(BigDecimal  amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Before(value = "execution(* pl.training.bank.Bank.payInCashToAccount(toAccountNumber, amount))"
            , argNames = "toAccountNumber, amount")
    public void payIn(String toAccountNumber, BigDecimal amount) {
        startLogEntry("payIn");
        log(toAccountNumber + " <= " + formatCurrency(amount));
    }

    @Before(value = "execution(* pl.training.bank.Bank.payOutCashFromAccount(fromAccountNumber, amount))"
            , argNames = "fromAccountNumber, amount")
    public void payOut(String fromAccountNumber, BigDecimal amount) {
        startLogEntry("payOut");
        log(fromAccountNumber + " => " + formatCurrency(amount));
    }

    @Before(value = "execution(* pl.training.bank.Bank.transferCash(fromAccountNumber, toAccountNumber, amount))"
            , argNames = "fromAccountNumber, toAccountNumber, amount")
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        startLogEntry("transfer");
        log(fromAccountNumber + " => " + formatCurrency(amount) + " => " + toAccountNumber );
    }

    @AfterReturning("financialOperation()")
    public void afterSuccess() {
        log(get("success"));
    }

    @AfterThrowing(value = "financialOperation()", throwing = "ex")
    public void afterFailure(BankException ex) {
        log(get("failure") + "(" + ex.getClass().getSimpleName() + ")");
    }

    @After("financialOperation()")
    public void endLogEntry() {
        log(ENTRY_SEPARATOR);
    }

}
