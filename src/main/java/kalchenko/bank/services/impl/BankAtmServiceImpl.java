package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankAtm;
import kalchenko.bank.repositories.BankAtmRepository;
import kalchenko.bank.services.BankAtmService;
import kalchenko.bank.services.BankOfficeService;

import java.math.BigDecimal;

public class BankAtmServiceImpl implements BankAtmService {

    private final BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
    private final BankOfficeService bankOfficeService;

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
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.getBankAtm();
        if(bankAtm.isPaymentAvailable()) {
            return bankOfficeService.withdrawMoney(bankAtm.getBankOffice().getId(),money);
        }
        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.getBankAtm();
        if(bankAtm.isDepositAvailable()){
            return bankOfficeService.depositMoney(bankAtm.getBankOffice().getId(),money);
        }
        return false;
    }
}
