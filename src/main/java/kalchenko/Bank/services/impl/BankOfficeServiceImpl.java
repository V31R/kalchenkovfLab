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

    @Override
    public BankOffice addBankOffice(BankOffice bankOffice) {

        if(bankOfficeRepository.add(bankOffice)){

            bankService.addOffice();
            return bankOfficeRepository.getBankOffice();

        }
        return null;
    }


    @Override
    public BankOffice getBankOffice() {
        return bankOfficeRepository.getBankOffice();
    }

    @Override
    public boolean deleteBankOffice() {
        return bankOfficeRepository.delete() && bankService.deleteOffice();
    }

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

    @Override
    public boolean addEmployee() {
        return bankService.addEmployee();
    }

    @Override
    public boolean deleteEmployee() {
        return bankService.deleteEmployee();
    }

    @Override
    public boolean withdrawMoney(BigDecimal money) {
        if(bankOfficeRepository.getBankOffice().isCanPaymentOfMoney()){
            return bankService.withdrawMoney(money);
        }

        return false;
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        if(bankOfficeRepository.getBankOffice().isCanDepositMoney()){
            return bankService.depositMoney(money);
        }

        return false;
    }
}
