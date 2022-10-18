package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

    private BankRepository bankRepository = new BankRepository();

    public BankServiceImpl() {}
    /*
    * Добавляет bank в репозиторий
    */
    @Override
    public Bank addBank(Bank bank) {
        if(bankRepository.add(bank)){

            return bankRepository.getBank();

        }

        return null;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public Bank getBank() {
        return bankRepository.getBank();
    }

    /*
     * Списывает деньги со счёта банка.
     */
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

    /*
     * Вносит деньги на счёт банка.
     */
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

    /*
     * Увеличивает число офисов.
     */
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

    /*
     * Уменьшает число офисов.
     */
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

    /*
     * Увеличивает число банкоматов.
     */
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

    /*
     * Уменьшает число банкоматов.
     */
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

    /*
     * Увеличивает число работников.
     */
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

    /*
     * Уменьшает число работников.
     */
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

    /*
     * Увеличивает число пользователей.
     */
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

    /*
     * Уменьшает число пользователей.
     */
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
