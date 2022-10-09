package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.Bank;
import kalchenko.Bank.repositories.BankRepository;
import kalchenko.Bank.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

    BankRepository bankRepository = new BankRepository();

    public BankServiceImpl() {}

    @Override
    public Bank addBank(Bank bank) {
        if(bankRepository.add(bank)){

            return bankRepository.getBank();

        }

        return null;
    }

    @Override
    public Bank getBank() {
        return bankRepository.getBank();
    }

    @Override
    public boolean withdrawMoney(BigDecimal money) {
        var bank = bankRepository.getBank();

        if(bank != null && money.compareTo(bank.getMoneyAmount()) == -1){
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        var bank = bankRepository.getBank();

        if(bank != null ){
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addOffice() {
        var bank = bankRepository.getBank();

        if(bank != null){
            bank.setOfficesNumber(bank.getOfficesNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteOffice() {
        var bank = bankRepository.getBank();

        if(bank != null && bank.getOfficesNumber() > 0){
            bank.setOfficesNumber(bank.getOfficesNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addAtm() {
        var bank = bankRepository.getBank();

        if(bank != null){
            bank.setAtmNumber(bank.getAtmNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteAtm() {
        var bank = bankRepository.getBank();

        if(bank != null && bank.getAtmNumber() > 0){
            bank.setAtmNumber(bank.getAtmNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addEmployee() {
        var bank = bankRepository.getBank();

        if(bank != null){
            bank.setEmployeeNumber(bank.getEmployeeNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteEmployee() {
        var bank = bankRepository.getBank();

        if(bank != null && bank.getEmployeeNumber() > 0){
            bank.setEmployeeNumber(bank.getEmployeeNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addUser() {
        var bank = bankRepository.getBank();

        if(bank != null){
            bank.setUserNumber(bank.getUserNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteUser() {
        var bank = bankRepository.getBank();

        if(bank != null && bank.getUserNumber() > 0){
            bank.setUserNumber(bank.getUserNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }
}
