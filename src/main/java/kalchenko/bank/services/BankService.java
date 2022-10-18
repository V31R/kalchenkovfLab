package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;

public interface BankService extends BankOperations {

    public Bank getBank();
    public Bank addBank(Bank bank);

    public boolean addOffice();
    public boolean deleteOffice();

    public boolean addAtm();
    public boolean deleteAtm();

    public boolean addEmployee();
    public boolean deleteEmployee();

    public boolean addUser();
    public boolean deleteUser();


}
