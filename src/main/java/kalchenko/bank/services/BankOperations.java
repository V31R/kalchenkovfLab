package kalchenko.bank.services;

import java.math.BigDecimal;

public interface BankOperations {

    /**
     * Списывает деньги.
     */
    boolean withdrawMoney(BigDecimal money);
    /**
     * Вносит деньги.
     */
    boolean depositMoney(BigDecimal money);

}
