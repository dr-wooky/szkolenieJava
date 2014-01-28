package pl.training.bank.aspect;

import org.aspectj.lang.JoinPoint;
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

    @Pointcut("execution(* pl.training.bank.Bank.*Cash*(..))" )
    public void financialOperation() {
    }

    private void log(String entry) {
        System.out.println(entry);
    }

    private String get(String key, Object ... params) {
        return  messageSource.getMessage(key, params, Locale.getDefault());
    }

    private String formatCurrency(BigDecimal  amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    private void startLogEntry(JoinPoint joinPoint) {
        log(ENTRY_SEPARATOR);
        log(get("operationStarted") + get(joinPoint.getSignature().getName()));
    }

    @Before(value = "execution(* pl.training.bank.Bank.payInCashToAccount(..)) && args(toAccountNumber, amount)")
    public void payIn(JoinPoint jointPoint, String toAccountNumber, BigDecimal amount) {
        startLogEntry(jointPoint);
        log(toAccountNumber + " <= " + formatCurrency(amount));
    }

    @Before(value = "execution(* pl.training.bank.Bank.payOutCashFromAccount(..)) && args(fromAccountNumber, amount)")
    public void payOut(JoinPoint jointPoint, String fromAccountNumber, BigDecimal amount) {
        startLogEntry(jointPoint);
        log(fromAccountNumber + " => " + formatCurrency(amount));
    }

    @Before(value = "execution(* pl.training.bank.Bank.transferCash(..)) && args(fromAccountNumber, toAccountNumber, amount)")
    public void transfer(JoinPoint jointPoint, String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        startLogEntry(jointPoint);
        log(fromAccountNumber + " => " + formatCurrency(amount) + " => " + toAccountNumber );
    }

    @AfterReturning("financialOperation()")
    public void afterSuccess() {
        log(get("success"));
        log(ENTRY_SEPARATOR);
    }

    @AfterThrowing(value = "financialOperation()", throwing = "ex")
    public void afterFailure(BankException ex) {
        log(get("failure") + "(" + ex.getClass().getSimpleName() + ")");
        log(ENTRY_SEPARATOR);
    }

}
