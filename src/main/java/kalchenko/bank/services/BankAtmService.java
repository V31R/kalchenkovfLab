package kalchenko.bank.services;

import kalchenko.bank.entity.BankAtm;

public interface BankAtmService extends BankOperations{

    public BankAtm addBankAtm(BankAtm bankAtm);
    public BankAtm getBankAtm();
    public boolean deleteBankAtm();
}
