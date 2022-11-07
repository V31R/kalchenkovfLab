package kalchenko.bank;


import kalchenko.bank.entity.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;
import kalchenko.bank.utils.AtmStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Random;

public class Main {

    public static void main(String[] argv){
        Random random = new Random();

        BankService bankService = BankServiceImpl.getInstance();
        var rate =  random.nextInt(100);
        BigDecimal interestRate =  BigDecimal.valueOf(random.nextDouble()*(20.d - rate/10.d));
        BigDecimal money = BigDecimal.valueOf(random.nextInt(1_000_000));
        bankService.addBank(new Bank(1L, "Bank_1", rate, money, interestRate ));

        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        bankOfficeService.addBankOffice(new BankOffice(1L,"Office_1","Address_1",
                bankService.getBank(), true, true, true,
                true, true,bankService.getBank().getMoneyAmount(), BigDecimal.valueOf(10.5d)));

        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        employeeService.addEmployee(new Employee(1L, "Ivanov Ivan Ivanovich", LocalDate.now(), "job",
                true, bankOfficeService.getBankOffice(), true, BigDecimal.ONE));

        BankAtmService bankAtmService = new BankAtmServiceImpl(bankOfficeService);
        bankAtmService.addBankAtm(new BankAtm(1L, "atm_1", AtmStatus.WORKING, bankOfficeService.getBankOffice(),
                "next to exit", employeeService.getEmployee(), true, true,
                bankOfficeService.getBankOffice().getMoneyAmount(), BigDecimal.TEN));

        UserService userService = UserServiceImpl.getInstance();
        userService.addUser(new User(1L, "Ivanov Ivan Ivanovich", LocalDate.now(),
                BigDecimal.valueOf(random.nextDouble()*9_999 +1), "job", bankService.getBank()));

        PaymentAccountService paymentAccountService =PaymentAccountServiceImpl.getInstance();
        paymentAccountService.addPaymentAccount(new PaymentAccount(1L, userService.getUser(),
                bankService.getBank().getName()));

        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();
        creditAccountService.addCreditAccount(new CreditAccount(1L, userService.getUser(),
                bankService.getBank().getName(), LocalDate.now(), 12, BigDecimal.valueOf(1000L),
                bankService.getBank().getInterestRate(), employeeService.getEmployee(),
                paymentAccountService.getPaymentAccount()));

        System.out.println(bankService.getBank());
        System.out.println(bankOfficeService.getBankOffice());
        System.out.println(employeeService.getEmployee());
        System.out.println(bankAtmService.getBankAtm());
        System.out.println(userService.getUser());
        System.out.println(paymentAccountService.getPaymentAccount());
        System.out.println(creditAccountService.getCreditAccount());

    }

}
