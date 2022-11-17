package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.repositories.BankOfficeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.BankService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

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

    private static final Random RANDOM = new Random();
    private static final double MONEY_DISPERSION = 0.1d;
    private static final double MIN_MONEY = 0.2d;
    private static int number = 0;
    public BankOffice createBankOffice(Bank bank) {
        final BigDecimal rent = BigDecimal.valueOf(10.5d);
        final boolean isWorking = true;
        final boolean hasAtm = true;
        final boolean canApplyLoan = true;
        final boolean canPayment = true;
        final boolean canDeposit = true;
        final var n = number++;

        final BigDecimal officeMoney = bank.getMoneyAmount().multiply(
                BigDecimal.valueOf((RANDOM.nextDouble()* MONEY_DISPERSION + MIN_MONEY))
        );

        return new BankOffice(String.format("Office_%d", n), String.format("Address_%d", n), bank, isWorking, hasAtm,
                canApplyLoan, canPayment, canDeposit, officeMoney, rent);
    }

    @Override
    public BankOffice addBankOffice(BankOffice bankOffice) {
        var newBankOffice = bankOfficeRepository.add(bankOffice);

        var bank = bankService.getBankById(bankOffice.getBank().getId());

        if (bank != null) {
            bankService.withdrawMoney(bank.getId(), bankOffice.getMoneyAmount());
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
    public List<BankOffice> getAllBankOffices() {
        return bankOfficeRepository.findAll();
    }

    @Override
    public List<BankOffice> getAllBankOfficesByBankId(Long bankId) {
        return bankOfficeRepository.findAll().stream()
                .filter(bankOffice -> bankOffice.getBank().getId().compareTo(bankId) == 0)
                .toList();
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

        if (bankOffice != null && bankOffice.isPaymentAvailable() && money.compareTo(bankOffice.getMoneyAmount()) == -1) {
            bankOffice.setMoneyAmount(bankOffice.getMoneyAmount().subtract(money));
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankOffice = bankOfficeRepository.findById(id);
        if (bankOffice != null && bankOffice.isDepositAvailable()) {
            bankOffice.setMoneyAmount(bankOffice.getMoneyAmount().add(money));
            bankOfficeRepository.update(bankOffice);
            return true;
        }

        return false;
    }
}
