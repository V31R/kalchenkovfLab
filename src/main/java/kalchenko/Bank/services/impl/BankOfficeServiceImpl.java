package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.BankOffice;
import kalchenko.Bank.repositories.BankOfficeRepository;
import kalchenko.Bank.services.BankOfficeService;

import java.math.BigDecimal;

public class BankOfficeServiceImpl implements BankOfficeService {


    BankServiceImpl bankService;

    BankOfficeRepository bankOfficeRepository = new BankOfficeRepository();

    public BankOfficeServiceImpl(BankServiceImpl bankService) {
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
        return bankOfficeRepository.delete();
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
        return bankService.withdrawMoney(money);
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        return bankService.depositMoney(money);
    }
}
