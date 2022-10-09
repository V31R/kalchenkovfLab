package kalchenko.Bank.services;

import kalchenko.Bank.entity.BankOffice;

public interface BankOfficeService extends BankOperations {

    public BankOffice addBankOffice(BankOffice bankOffice);

    public BankOffice getBankOffice();

    public boolean deleteBankOffice();

    public boolean addAtm();
    public boolean deleteAtm();

    public boolean addEmployee();
    public boolean deleteEmployee();

}
