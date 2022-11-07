package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.repositories.BankOfficeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;

public class BankOfficeServiceImpl implements BankOfficeService {

    private static BankOfficeServiceImpl INSTANCE;

    private BankOfficeServiceImpl(){}

    public static BankOfficeServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankOfficeServiceImpl();
        }

        return INSTANCE;
    }

    private final BankService bankService = BankServiceImpl.getInstance();

    private final BankOfficeRepository bankOfficeRepository = BankOfficeRepository.getInstance();

    @Override
    public BankOffice addBankOffice(BankOffice bankOffice) {

        if(bankOfficeRepository.add(bankOffice)){
            var bank = bankService.getBankById(bankOffice.getBank().getId());

            if(bank != null){
                bank.setOfficesNumber(bank.getOfficesNumber() + 1);
                bankService.update(bank);
            }

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

        var bankId = bankOfficeRepository.getBankOffice().getId();

        if(!bankOfficeRepository.delete()) {
            return false;
        }
        var bank =bankService.getBankById(bankId);

        if(bank != null && bank.getOfficesNumber() > 0){
            bank.setOfficesNumber(bank.getOfficesNumber() - 1);
            bankService.update(bank);
            return true;
        }

        return false;
    }



    @Override
    public boolean addAtm() {
        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice == null){
            return false;
        }
        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if(bank != null){
            bank.setAtmNumber(bank.getAtmNumber() + 1);
            bankService.update(bank);
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() + 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }

    /**
     * Уменьшает число банкоматов, при добавлении, извещает об этом связанный bank.
     */
    @Override
    public boolean deleteAtm() {
        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice == null){
            return false;
        }

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if(bank != null && bank.getAtmNumber() > 0 && bankOffice.getAtmNumber() > 0){
            bank.setAtmNumber(bank.getAtmNumber() - 1);
            bankService.update(bank);
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() - 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }


    @Override
    public boolean addEmployee() {

        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice == null){
            return false;
        }
        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if(bank != null){
            bank.setEmployeeNumber(bank.getEmployeeNumber() + 1);
            bankService.update(bank);
            return true;
        }

        return false;

    }


    @Override
    public boolean deleteEmployee() {
        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice == null){
            return false;
        }

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if(bank != null && bank.getEmployeeNumber() > 0){
            bank.setEmployeeNumber(bank.getEmployeeNumber() - 1);
            bankService.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice.isPaymentAvailable()){
            return bankService.withdrawMoney(bankOffice.getBank().getId(), money);
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankOffice = bankOfficeRepository.getBankOffice();
        if(bankOffice.isDepositAvailable()){
            return bankService.depositMoney(bankOffice.getBank().getId(), money);
        }

        return false;
    }
}
