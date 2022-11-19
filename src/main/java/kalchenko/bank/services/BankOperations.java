package kalchenko.bank.services;

import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.NegativeSumException;

import java.math.BigDecimal;

public interface BankOperations {

    /**
     * Списывает деньги по id.
     */
    boolean withdrawMoney(Long id, BigDecimal money) throws NegativeSumException, IdException;
    /**
     * Вносит деньги по id.
     */
    boolean depositMoney(Long id, BigDecimal money) throws NegativeSumException, IdException;

}
