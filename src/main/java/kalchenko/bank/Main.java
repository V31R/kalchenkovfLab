package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.exceptions.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random RANDOM = new Random();
    private static final double MIN_CREDIT_SUM = 1000;
    private static final double CREDIT_SUM_DISPERSION = 10_000;

    public static void main(String[] argv) {

        // Количество сущностей согласно заданию
        final int banksNumber = 5;
        final int officesNumber = 3;
        final int employeeNumber = 5;
        final int usersNumber = 5;
        final int accountsNumber = 1;

        BankService bankService = BankServiceImpl.getInstance();
        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        BankAtmService bankAtmService = BankAtmServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();

        for (int i = 0; i < banksNumber; i++) {
            var bank = bankService.addBank(bankService.createBank());
            Employee employeeForAccount = null;
            for (int j = 0; j < officesNumber; j++) {
                var bankOffice = bankOfficeService.addBankOffice(bankOfficeService.createBankOffice(bank));
                Employee employee = null;
                for (int k = 0; k < employeeNumber; k++) {
                    employee = employeeService.addEmployee(employeeService.createEmployee(bankOffice));
                    if (employeeForAccount == null) {
                        employeeForAccount = employee;
                    }
                }
                bankAtmService.addBankAtm(bankAtmService.createBankAtm(bankOffice, employee));

            }
            for (int j = 0; j < usersNumber; j++) {
                var user = userService.addUser(userService.createUser(bank));
                for (int k = 0; k < accountsNumber; k++) {
                    paymentAccountService.addPaymentAccount(paymentAccountService.createPaymentAccount(bank, user));
                }
            }
        }

        System.out.println("Available users:");
        userService.getAllUsers().forEach(user -> System.out.printf("User id = %d  name = %s\n",user.getId(), user.getFullName()));
        Long userId = 0L;
        Scanner input = new Scanner(System.in);
        while(userId.compareTo(0L) <= 0 || userId.compareTo((long)usersNumber) == 1) {
            System.out.println("Enter user id:");
            userId = input.nextLong();
        }

        try {
            var creditId = bankService.issueLoan(userId, BigDecimal.valueOf(RANDOM.nextDouble() * CREDIT_SUM_DISPERSION + MIN_CREDIT_SUM));
            System.out.println("Managed to get a loan #" + creditId);
        }catch (LendingTermsException lendingTermsException){
            System.out.println("Failed to get a loan");
        }
        bankService.outputBankInfo(bankService.getAllBanks().get(0).getId(), System.out);
        userService.outputUserAccounts(userId, System.out);

    }

}
