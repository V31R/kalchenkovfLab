package kalchenko.bank.services;

import java.math.BigDecimal;

public interface BankOperations {

    /**
     * Списывает деньги.
     */
    boolean withdrawMoney(Long id, BigDecimal money);
    /**
     * Вносит деньги.
     */
    boolean depositMoney(Long id, BigDecimal money);

}
