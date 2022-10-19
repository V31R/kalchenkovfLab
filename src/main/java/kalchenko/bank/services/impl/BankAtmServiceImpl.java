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
        if(bankAtmRepository.getBankAtm().isPaymentAvailable()) {
            return bankOfficeService.withdrawMoney(money);
        }
        return false;
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        if(bankAtmRepository.getBankAtm().isDepositAvailable()){
            return bankOfficeService.depositMoney(money);
        }
        return false;
    }
}
