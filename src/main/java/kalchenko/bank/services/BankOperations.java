package kalchenko.bank.services;

import java.math.BigDecimal;

public interface BankOperations {

    /**
     * Списывает деньги по id.
     */
    boolean withdrawMoney(Long id, BigDecimal money);
    /**
     * Вносит деньги по id.
     */
    boolean depositMoney(Long id, BigDecimal money);

}
