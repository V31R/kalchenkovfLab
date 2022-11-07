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

            bankService.addOffice(bankOffice.getBank().getId());
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
        var id = bankOfficeRepository.getBankOffice().getId();
        return bankOfficeRepository.delete() && bankService.deleteOffice(id);
    }


    @Override
    public boolean addAtm() {
        var bankOffice = bankOfficeRepository.getBankOffice();

        if(bankOffice != null && bankService.addAtm(bankOffice.getBank().getId())){
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

        if(bankOffice != null && bankService.deleteAtm(bankOffice.getBank().getId())){
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() - 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }


    @Override
    public boolean addEmployee() {
        var id = bankOfficeRepository.getBankOffice().getId();
        return bankService.addEmployee(id);
    }


    @Override
    public boolean deleteEmployee() {
        var id = bankOfficeRepository.getBankOffice().getId();
        return bankService.deleteEmployee(id);
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
