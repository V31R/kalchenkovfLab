package kalchenko.Bank.services;

import java.math.BigDecimal;

public interface BankOperations {

    public boolean withdrawMoney(BigDecimal money);
    public boolean depositMoney(BigDecimal money);

}