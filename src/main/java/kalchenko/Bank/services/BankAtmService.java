package kalchenko.Bank.services;

import kalchenko.Bank.entity.BankAtm;

public interface BankAtmService extends BankOperations{

    public BankAtm addBankAtm(BankAtm bankAtm);
    public BankAtm getBankAtm();
    public boolean deleteBankAtm();
}
