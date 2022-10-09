package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.BankAtm;
import kalchenko.Bank.services.BankAtmService;

import java.math.BigDecimal;

public class BankAtmServiceImpl implements BankAtmService {

    @Override
    public BankAtm getBankAtm() {
        return null;
    }

    @Override
    public boolean deleteBankAtm() {
        return false;
    }

    @Override
    public boolean withdrawMoney(BigDecimal money) {
        return false;
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        return false;
    }
}
