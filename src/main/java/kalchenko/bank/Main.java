package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;

public class Main {


    public static void main(String[] argv) {

        BankService bankService = BankServiceImpl.getInstance();
        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        BankAtmService bankAtmService = BankAtmServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        PaymentAccountService paymentAccountService = PaymentAccountServiceImpl.getInstance();
        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();

        for (int banksNumber = 0; banksNumber < 5; banksNumber++) {
            var bank = bankService.addBank(bankService.createBank());
            Employee employeeForAccount = null;
            for (int officesNumber = 0; officesNumber < 3; officesNumber++) {
                var bankOffice = bankOfficeService.addBankOffice(bankOfficeService.createBankOffice(bank));
                Employee employee = null;
                for (int employeeNumber = 0; employeeNumber < 5; employeeNumber++) {
                    employee = employeeService.addEmployee(employeeService.createEmployee(bankOffice));
                    if (employeeForAccount == null) {
                        employeeForAccount = employee;
                    }
                }
                bankAtmService.addBankAtm(bankAtmService.createBankAtm(bankOffice, employee));

            }
            for (int usersNumber = 0; usersNumber < 5; usersNumber++) {
                var user = userService.addUser(userService.createUser(bank));
                for (int accountsNumber = 0; accountsNumber < 3; accountsNumber++) {
                    var paymentAccount = paymentAccountService.addPaymentAccount(paymentAccountService.createPaymentAccount(bank, user));
                    creditAccountService.addCreditAccount(creditAccountService.createCreditAccount(bank, user, paymentAccount, employeeForAccount));
                }
            }
        }


        bankService.outputBankInfo(bankService.getAllBanks().get(0).getId(), System.out);
        userService.outputUserAccounts(userService.getAllUsers().get(0).getId(), System.out);

    }

}
