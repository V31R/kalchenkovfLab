package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.exceptions.*;
import kalchenko.bank.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class Main implements ApplicationRunner {

    @Autowired
    private BankServiceImpl bankService;

    @Autowired
    private BankOfficeServiceImpl bankOfficeService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private BankAtmServiceImpl bankAtmService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PaymentAccountServiceImpl paymentAccountService;

    private static final Random RANDOM = new Random();
    private static final double MIN_CREDIT_SUM = 1000;
    private static final double CREDIT_SUM_DISPERSION = 10_000;

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception{
        // Количество сущностей согласно заданию
        final int banksNumber = 5;
        final int officesNumber = 3;
        final int employeeNumber = 5;
        final int usersNumber = 5;
        final int accountsNumber = 1;

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
        var usersNumberId =  userService.getAllUsers().size();
        Long userId = 0L;
        Scanner input = new Scanner(System.in);
        while(userId.compareTo(0L) <= 0 || userId.compareTo((long)usersNumberId) == 1) {
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

        bankService.exportBankAccounts(bankService.getAllBanks().get(0).getId(), "/tmp/accounts.txt");

        bankService.transferBankAccounts(bankService.getAllBanks().get(0).getId(), bankService.getAllBanks().get(1).getId());

        userService.outputUserAccounts(userId, System.out);
    }

}
