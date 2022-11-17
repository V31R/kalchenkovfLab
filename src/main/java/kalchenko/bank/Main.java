package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;

import java.math.BigDecimal;

public class Main {


    public static void main(String[] argv) {

        // Количество сущностей согласно заданию
        final int banksNumber = 5;
        final int officesNumber = 3;
        final int employeeNumber = 5;
        final int usersNumber = 5;
        final int accountsNumber = 2;

        BankService bankService = BankServiceImpl.getInstance();
        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        BankAtmService bankAtmService = BankAtmServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();
        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();

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
                    var paymentAccount = paymentAccountService.addPaymentAccount(paymentAccountService.createPaymentAccount(bank, user));
                    creditAccountService.addCreditAccount(creditAccountService.createCreditAccount(bank, user, paymentAccount, employeeForAccount));
                }
            }
        }

        var userId = userService.getAllUsers().get(0).getId();
        var creditId = userService.getCredit(userId, BigDecimal.valueOf(1000d));
        System.out.println(creditId);
        bankService.outputBankInfo(bankService.getAllBanks().get(0).getId(), System.out);
        userService.outputUserAccounts(userId, System.out);

    }

}
