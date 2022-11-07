package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;
import java.util.List;

public class BankServiceImpl implements BankService {

    private static BankServiceImpl INSTANCE;

    private BankServiceImpl(){}

    public static BankServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankServiceImpl();
        }

        return INSTANCE;
    }

    private final BankRepository bankRepository = BankRepository.getInstance();


    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.add(bank);
    }

    @Override
    public Bank getBankById(Long id) {

        return bankRepository.findById(id);

    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if(bank != null && money.compareTo(bank.getMoneyAmount()) == -1){
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if(bank != null ){
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addOffice(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null){
            bank.setOfficesNumber(bank.getOfficesNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteOffice(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null && bank.getOfficesNumber() > 0){
            bank.setOfficesNumber(bank.getOfficesNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addAtm(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null){
            bank.setAtmNumber(bank.getAtmNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteAtm(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null && bank.getAtmNumber() > 0){
            bank.setAtmNumber(bank.getAtmNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public boolean addEmployee(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null){
            bank.setEmployeeNumber(bank.getEmployeeNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public boolean deleteEmployee(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null && bank.getEmployeeNumber() > 0){
            bank.setEmployeeNumber(bank.getEmployeeNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean addUser(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null){
            bank.setUserNumber(bank.getUserNumber() + 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteUser(Long bankId) {
        var bank = bankRepository.findById(bankId);

        if(bank != null && bank.getUserNumber() > 0){
            bank.setUserNumber(bank.getUserNumber() - 1);
            bankRepository.update(bank);
            return true;
        }

        return false;
    }
}
