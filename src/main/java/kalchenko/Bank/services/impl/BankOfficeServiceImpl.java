package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.BankOffice;
import kalchenko.Bank.repositories.BankOfficeRepository;
import kalchenko.Bank.services.BankOfficeService;
import kalchenko.Bank.services.BankService;

import java.math.BigDecimal;

public class BankOfficeServiceImpl implements BankOfficeService {

    BankService bankService;

    BankOfficeRepository bankOfficeRepository = new BankOfficeRepository();

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /*
     * Добавляет bankOffice в репозиторий, если добавление было успешно
     * извещает об этом связанный bank.
     */
    @Override
    public BankOffice addBankOffice(BankOffice bankOffice) {

        if(bankOfficeRepository.add(bankOffice)){

            bankService.addOffice();
            return bankOfficeRepository.getBankOffice();

        }
        return null;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public BankOffice getBankOffice() {
        return bankOfficeRepository.getBankOffice();
    }

    /*
     * Удаляет объект, извещает об этом связанный Bank.
     */
    @Override
    public boolean deleteBankOffice() {
        return bankOfficeRepository.delete() && bankService.deleteOffice();
    }

    /*
     * Увеличивает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    @Override
    public boolean addAtm() {
        var bankOffice = bankOfficeRepository.getBankOffice();

        if(bankOffice != null && bankService.addAtm()){
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() + 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }

    /*
     * Уменьшает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    @Override
    public boolean deleteAtm() {
        var bankOffice = bankOfficeRepository.getBankOffice();

        if(bankOffice != null && bankService.deleteAtm()){
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() - 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }

    /*
     * Извещает связанный bank об увеличении числа пользоваталей.
     */
    @Override
    public boolean addEmployee() {
        return bankService.addEmployee();
    }

    /*
     * Извещает связанный bank об уменьшении числа пользоваталей.
     */
    @Override
    public boolean deleteEmployee() {
        return bankService.deleteEmployee();
    }

    /*
     * Списывает деньги из связанного объекта bank.
     */
    @Override
    public boolean withdrawMoney(BigDecimal money) {
        if(bankOfficeRepository.getBankOffice().isCanPaymentOfMoney()){
            return bankService.withdrawMoney(money);
        }

        return false;
    }

    /*
     * Вносит деньги в связанный объекта bank.
     */
    @Override
    public boolean depositMoney(BigDecimal money) {
        if(bankOfficeRepository.getBankOffice().isCanDepositMoney()){
            return bankService.depositMoney(money);
        }

        return false;
    }
}
