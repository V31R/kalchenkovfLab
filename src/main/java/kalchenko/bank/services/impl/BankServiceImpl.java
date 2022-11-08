package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.repositories.*;
import kalchenko.bank.services.BankService;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

public class BankServiceImpl implements BankService {

    private static BankServiceImpl INSTANCE;

    private BankServiceImpl(){}

    public static BankServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankServiceImpl();
        }

        return INSTANCE;
    }

    private final BankRepository bankRepository = BankRepository.getInstance();


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

        if(bank != null && money.compareTo(bank.getMoneyAmount()) == -1){
            bank.setMoneyAmount(bank.getMoneyAmount().subtract(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var bank = bankRepository.findById(id);

        if(bank != null ){
            bank.setMoneyAmount(bank.getMoneyAmount().add(money));
            bankRepository.update(bank);
            return true;
        }

        return false;
    }


    @Override
    public void outputBankInfo(Long bankId, OutputStream outputStream) {

        PrintStream printStream = new PrintStream(outputStream);

        printStream.printf("Bank data about %s\n", bankRepository.findById(bankId).getName());

        BankOfficeRepository bankOfficeRepository = BankOfficeRepository.getInstance();
        BankAtmRepository bankAtmRepository = BankAtmRepository.getInstance();
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
        UserRepository userRepository = UserRepository.getInstance();

        var bankOffices = bankOfficeRepository.findAll().stream()
                .filter(bankOffice -> bankOffice.getBank().getId().compareTo(bankId) == 0)
                .toList();
        if(bankOffices.size() > 0) {
            printStream.println("Bank offices:");
            for (var bankOffice : bankOffices) {
                printStream.println(bankOffice);

            }
        }else{
            printStream.println("User does not have offices");
        }

        var bankAtms = bankAtmRepository.findAll().stream()
                .filter(bankAtm -> bankAtm.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if(bankAtms.size() > 0) {
            printStream.println("Bank ATMs:");
            for (var bankAtm : bankAtms) {

                printStream.println(bankAtm);

            }
        }else{
            printStream.println("Bank does not ATM");
        }

        var employees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getBankOffice().getBank().getId().compareTo(bankId) == 0)
                .toList();

        if(employees.size() > 0) {
            printStream.println("Bank employees:");
            for (var employee : employees) {

                printStream.println(employee);

            }
        }else{
            printStream.println("Bank does not employees");
        }

        var users = userRepository.findAll().stream()
                .filter(
                    user -> user.getBanks().stream()
                            .filter(bank -> bank.getId().compareTo(bankId) == 0)
                            .toList()
                            .size() > 0
                )
                .toList();

        if(users.size() > 0) {
            printStream.println("Bank users:");
            for (var user : users) {

                printStream.println(user);

            }
        }else{
            printStream.println("Bank does not user");
        }

    }
}
