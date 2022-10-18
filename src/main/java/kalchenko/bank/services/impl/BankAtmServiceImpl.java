package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankAtm;
import kalchenko.bank.repositories.BankAtmRepository;
import kalchenko.bank.services.BankAtmService;
import kalchenko.bank.services.BankOfficeService;

import java.math.BigDecimal;

public class BankAtmServiceImpl implements BankAtmService {

    private BankAtmRepository bankAtmRepository = new BankAtmRepository();
    private BankOfficeService bankOfficeService;

    public BankAtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    /*
    * Добавляет bankAtm в репозиторий, если добавление было успешно
    * извещает об этом связанный bankOffice.
    */
    @Override
    public BankAtm addBankAtm(BankAtm bankAtm) {
        if(bankAtmRepository.add(bankAtm)){
            bankOfficeService.addAtm();
            return  bankAtmRepository.getBankAtm();
        }

        return null;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public BankAtm getBankAtm() {
        return bankAtmRepository.getBankAtm();
    }

    /*
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    @Override
    public boolean deleteBankAtm() {
        if(bankAtmRepository.delete()){

            return bankOfficeService.deleteAtm();

        }
        return false;
    }

    /*
     * Списывает деньги из связанного объекта bankOffice.
     */
    @Override
    public boolean withdrawMoney(BigDecimal money) {
        if(bankAtmRepository.getBankAtm().isCanPaymentOfMoney()) {
            return bankOfficeService.withdrawMoney(money);
        }
        return false;
    }

    /*
     * Вносит деньги в связанный объекта bankOffice.
     */
    @Override
    public boolean depositMoney(BigDecimal money) {
        if(bankAtmRepository.getBankAtm().isCanDepositMoney()){
            return bankOfficeService.depositMoney(money);
        }
        return false;
    }
}
