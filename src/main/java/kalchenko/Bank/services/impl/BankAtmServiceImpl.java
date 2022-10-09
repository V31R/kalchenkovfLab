package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.BankAtm;
import kalchenko.Bank.repositories.BankAtmRepository;
import kalchenko.Bank.services.BankAtmService;
import kalchenko.Bank.services.BankOfficeService;

import java.math.BigDecimal;

public class BankAtmServiceImpl implements BankAtmService {

    BankAtmRepository bankAtmRepository = new BankAtmRepository();
    BankOfficeServiceImpl bankOfficeService;

    public BankAtmServiceImpl(BankOfficeServiceImpl bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public BankAtm addBankAtm(BankAtm bankAtm) {
        if(bankAtmRepository.add(bankAtm)){
            bankOfficeService.addAtm();
            return  bankAtmRepository.getBankAtm();
        }

        return null;
    }

    @Override
    public BankAtm getBankAtm() {
        return bankAtmRepository.getBankAtm();
    }

    @Override
    public boolean deleteBankAtm() {
        if(bankAtmRepository.delete()){

            return bankOfficeService.deleteAtm();

        }
        return false;
    }

    @Override
    public boolean withdrawMoney(BigDecimal money) {
        if(bankAtmRepository.getBankAtm().isCanPaymentOfMoney()) {
            return bankOfficeService.withdrawMoney(money);
        }
        return false;
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        if(bankAtmRepository.getBankAtm().isCanDepositMoney()){
            return bankOfficeService.depositMoney(money);
        }
        return false;
    }
}
