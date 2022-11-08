package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.repositories.BankOfficeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс-одиночка
 */
public class BankOfficeServiceImpl implements BankOfficeService {

    private static BankOfficeServiceImpl INSTANCE;

    private BankOfficeServiceImpl() {
    }

    public static BankOfficeServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankOfficeServiceImpl();
        }

        return INSTANCE;
    }

    private final BankService bankService = BankServiceImpl.getInstance();

    private final BankOfficeRepository bankOfficeRepository = BankOfficeRepository.getInstance();

    @Override
    public BankOffice addBankOffice(BankOffice bankOffice) {
        var newBankOffice = bankOfficeRepository.add(bankOffice);

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null) {
            bank.setOfficesNumber(bank.getOfficesNumber() + 1);
            bankService.update(bank);
        }

        return newBankOffice;
    }


    @Override
    public BankOffice getBankOfficeById(Long id) {
        return bankOfficeRepository.findById(id);
    }

    @Override
    public List<BankOffice> getAllBanks() {
        return bankOfficeRepository.findAll();
    }

    @Override
    public boolean deleteBankOfficeById(Long id) {

        var bankId = bankOfficeRepository.findById(id).getId();

        if (!bankOfficeRepository.deleteById(id)) {
            return false;
        }
        var bank = bankService.getBankById(bankId);

        if (bank != null && bank.getOfficesNumber() > 0) {
            bank.setOfficesNumber(bank.getOfficesNumber() - 1);
            bankService.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public boolean addAtm(Long bankOfficeId) {
        var bankOffice = bankOfficeRepository.findById(bankOfficeId);
        if (bankOffice == null) {
            return false;
        }
        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null) {
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
    public boolean deleteAtm(Long bankOfficeId) {
        var bankOffice = bankOfficeRepository.findById(bankOfficeId);
        if (bankOffice == null) {
            return false;
        }

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null && bank.getAtmNumber() > 0 && bankOffice.getAtmNumber() > 0) {
            bank.setAtmNumber(bank.getAtmNumber() - 1);
            bankService.update(bank);
            bankOffice.setAtmNumber(bankOffice.getAtmNumber() - 1);
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }


    @Override
    public boolean addEmployee(Long bankOfficeId) {

        var bankOffice = bankOfficeRepository.findById(bankOfficeId);
        if (bankOffice == null) {
            return false;
        }
        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null) {
            bank.setEmployeeNumber(bank.getEmployeeNumber() + 1);
            bankService.update(bank);
            return true;
        }

        return false;

    }


    @Override
    public boolean deleteEmployee(Long bankOfficeId) {
        var bankOffice = bankOfficeRepository.findById(bankOfficeId);
        if (bankOffice == null) {
            return false;
        }

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null && bank.getEmployeeNumber() > 0) {
            bank.setEmployeeNumber(bank.getEmployeeNumber() - 1);
            bankService.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bankOffice = bankOfficeRepository.findById(id);
        if (bankOffice.isPaymentAvailable()) {
            return bankService.withdrawMoney(bankOffice.getBank().getId(), money);
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankOffice = bankOfficeRepository.findById(id);
        if (bankOffice.isDepositAvailable()) {
            return bankService.depositMoney(bankOffice.getBank().getId(), money);
        }

        return false;
    }
}
