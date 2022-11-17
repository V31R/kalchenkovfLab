package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankAtm;
import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;
import kalchenko.bank.repositories.BankAtmRepository;
import kalchenko.bank.services.BankAtmService;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.utils.AtmStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Класс-одиночка
 */
public class BankAtmServiceImpl implements BankAtmService {

    private static BankAtmServiceImpl INSTANCE;

    private BankAtmServiceImpl() {
    }

    public static BankAtmServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAtmServiceImpl();
        }

        return INSTANCE;
    }

    private final BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
    private final BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();

    private static final Random RANDOM = new Random();
    private static final double MONEY_DISPERSION = 0.2d;
    private static final double MIN_MONEY = 0.3d;

    private static int number = 0;
    public BankAtm createBankAtm(BankOffice bankOffice, Employee employee) {
        final BigDecimal rent = BigDecimal.TEN;
        final boolean canPayment = true;
        final boolean canDeposit = true;
        final AtmStatus status = AtmStatus.WORKING;

        final BigDecimal atmMoney = bankOffice.getMoneyAmount().multiply(
                BigDecimal.valueOf((RANDOM.nextDouble()* MONEY_DISPERSION + MIN_MONEY))
        );

        return new BankAtm(String.format("Atm_%d", number++), status, bankOffice, "next to exit",
                employee, canPayment, canDeposit, atmMoney, rent);
    }


    @Override
    public BankAtm addBankAtm(BankAtm bankAtm) {

        var newBankAtm = bankAtmRepository.add(bankAtm);
        var office = newBankAtm.getBankOffice();

        if (office != null) {
            bankOfficeService.withdrawMoney(office.getId(), newBankAtm.getMoneyAmount());
            bankOfficeService.addAtm(office.getId());
        }
        return newBankAtm;

    }


    @Override
    public BankAtm getBankAtmById(Long id) {
        return bankAtmRepository.findById(id);
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    @Override
    public List<BankAtm> getAllBankAtmsByOffice(Long officeId) {
        return bankAtmRepository.findAll().stream()
                .filter(employee -> employee.getBankOffice().getId().compareTo(officeId) == 0)
                .toList();
    }

    @Override
    public boolean deleteBankAtmById(Long id) {

        var officeId = bankAtmRepository.findById(id).getId();

        if (bankAtmRepository.deleteById(id)) {

            return bankOfficeService.deleteAtm(officeId);

        }
        return false;
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.findById(id);
        if (bankAtm != null && bankAtm.isPaymentAvailable() && money.compareTo(bankAtm.getMoneyAmount()) == -1) {
            bankAtm.setMoneyAmount(bankAtm.getMoneyAmount().subtract(money));
            bankAtmRepository.update(bankAtm);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bankAtm = bankAtmRepository.findById(id);

        if (bankAtm != null && bankAtm.isDepositAvailable()) {
            bankAtm.setMoneyAmount(bankAtm.getMoneyAmount().add(money));
            bankAtmRepository.update(bankAtm);
            return true;
        }

        return false;
    }
}
