package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.repositories.*;
import kalchenko.bank.services.BankService;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Класс-одиночка
 */
public class BankServiceImpl implements BankService {

    private static BankServiceImpl INSTANCE;

    private BankServiceImpl() {
    }

    public static BankServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankServiceImpl();
        }

        return INSTANCE;
    }

    private final BankRepository bankRepository = BankRepository.getInstance();

    private static int number = 0;
    private static final Random RANDOM = new Random();
    private static final int MAX_RATE = 100;
    private static final double MAX_INTEREST_RATE = 20.d;
    private static final int MAX_MONEY = 1_000_000;

    public Bank createBank() {
        var rate = RANDOM.nextInt(MAX_RATE);
        //Поскольку рейтинг может быть до 100, то нужно уменьшить его в 10 раз, чтобы ставка не могла быть отрицательной
        BigDecimal interestRate = BigDecimal.valueOf(RANDOM.nextDouble() * (MAX_INTEREST_RATE - rate / 10.d));
        BigDecimal money = BigDecimal.valueOf(RANDOM.nextInt(MAX_MONEY));
        return new Bank(String.format("Bank_%d", number++), rate, money, interestRate);
    }

    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.add(bank);
    }

    @Override
    public Bank getBankById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank update(Bank bank) {
        return bankRepository.update(bank);
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if (bank != null && money.compareTo(bank.getMoneyAmount()) == -1) {
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if (bank != null) {
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public void outputBankInfo(Long bankId, OutputStream outputStream) {

        PrintStream printStream = new PrintStream(outputStream);

        var bank = bankRepository.findById(bankId);
        printStream.printf("Bank data about %s\n", bank.getName());
        printStream.println(bank);

        BankOfficeRepository bankOfficeRepository = BankOfficeRepository.getInstance();
        BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
        UserRepository userRepository = UserRepository.getInstance();

        var bankOffices = bankOfficeRepository.findAll().stream()
                .filter(bankOffice -> bankOffice.getBank().getId().compareTo(bankId) == 0)
                .toList();
        if (bankOffices.size() > 0) {
            printStream.println("Bank offices:");
            bankOffices.forEach(printStream::println);
        } else {
            printStream.println("User does not have offices");
        }

        var bankAtms = bankAtmRepository.findAll().stream()
                .filter(bankAtm -> bankAtm.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if (bankAtms.size() > 0) {
            printStream.println("Bank ATMs:");
            bankAtms.forEach(printStream::println);
        } else {
            printStream.println("Bank does not ATM");
        }

        var employees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if (employees.size() > 0) {
            printStream.println("Bank employees:");
            employees.forEach(printStream::println);
        } else {
            printStream.println("Bank does not employees");
        }

        var users = userRepository.findAll().stream()
                .filter(
                        user -> user.getBanks().stream()
                                .filter(b -> b.getId().compareTo(bankId) == 0)
                                .toList()
                                .size() > 0
                )
                .toList();

        if (users.size() > 0) {
            printStream.println("Bank users:");
            users.forEach(printStream::println);
        } else {
            printStream.println("Bank does not user");
        }

    }
}
