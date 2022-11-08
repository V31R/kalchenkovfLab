package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;
import kalchenko.bank.utils.AtmStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Random;

public class Main {

    private static final Random random = new Random();
    private static long number = 0L;

    public static void main(String[] argv) {

        BankService bankService = BankServiceImpl.getInstance();
        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        BankAtmService bankAtmService = BankAtmServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();
        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();

        for (int banksNumber = 0; banksNumber < 5; banksNumber++) {
            var bank = bankService.addBank(createBank());
            Employee employeeForAccount = null;
            for (int officesNumber = 0; officesNumber < 3; officesNumber++) {
                var bankOffice = bankOfficeService.addBankOffice(createBankOffice(bank));
                Employee employee = null;
                for (int employeeNumber = 0; employeeNumber < 5; employeeNumber++) {
                    employee = employeeService.addEmployee(createEmployee(bankOffice));
                    if (employeeForAccount == null) {
                        employeeForAccount = employee;
                    }
                }
                bankAtmService.addBankAtm(createBankAtm(bankOffice, employee));

            }
            for (int usersNumber = 0; usersNumber < 5; usersNumber++) {
                var user = userService.addUser(createUser(bank));
                for (int accountsNumber = 0; accountsNumber < 3; accountsNumber++) {
                    var paymentAccount = paymentAccountService.addPaymentAccount(createPaymentAccount(bank, user));
                    creditAccountService.addCreditAccount(createCreditAccount(bank, user, paymentAccount, employeeForAccount));
                }
            }
        }


        bankService.outputBankInfo(bankService.getAllBanks().get(0).getId(), System.out);
        userService.outputUserAccounts(userService.getAllUsers().get(0).getId(), System.out);

    }

    static Bank createBank() {
        var rate = random.nextInt(100);
        BigDecimal interestRate = BigDecimal.valueOf(random.nextDouble() * (20.d - rate / 10.d));
        BigDecimal money = BigDecimal.valueOf(random.nextInt(1_000_000));
        return new Bank(String.format("Bank_%d", number++), rate, money, interestRate);
    }

    static BankOffice createBankOffice(Bank bank) {
        var n = number++;

        return new BankOffice(String.format("Office_%d", n), String.format("Address_%d", n), bank, true, true,
                true, true, true, bank.getMoneyAmount(), BigDecimal.valueOf(10.5d));
    }

    static Employee createEmployee(BankOffice bankOffice) {
        return new Employee(String.format("Employee_name_%d", number++), LocalDate.now(), "job", true, bankOffice,
                true, BigDecimal.ONE);
    }

    static BankAtm createBankAtm(BankOffice bankOffice, Employee employee) {
        return new BankAtm(String.format("Atm_%d", number++), AtmStatus.WORKING, bankOffice, "next to exit",
                employee, true, true, bankOffice.getMoneyAmount(), BigDecimal.TEN);
    }


    static User createUser(Bank bank) {
        return new User(String.format("User_name_%d", number++), LocalDate.now(),
                BigDecimal.valueOf(random.nextDouble() * 9_999 + 1), "job", bank);
    }

    static PaymentAccount createPaymentAccount(Bank bank, User user) {
        return new PaymentAccount(user, bank.getName());
    }


    static CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee) {
        return new CreditAccount(user, bank.getName(), LocalDate.now(), 12, BigDecimal.valueOf(1000L),
                bank.getInterestRate(), employee, paymentAccount);
    }

}
