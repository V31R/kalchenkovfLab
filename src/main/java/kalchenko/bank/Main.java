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
        var bank = bankService.addBank(new Bank("Bank_1", rate, money, interestRate ));

        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        var  bankOffice =bankOfficeService.addBankOffice(new BankOffice("Office_1","Address_1",
                bank, true, true, true,
                true, true, bank.getMoneyAmount(), BigDecimal.valueOf(10.5d)));

        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        var employee =employeeService.addEmployee(new Employee("Ivanov Ivan Ivanovich", LocalDate.now(), "job",
                true, bankOffice, true, BigDecimal.ONE));

        BankAtmService bankAtmService = new BankAtmServiceImpl(bankOfficeService);
        var bankAtm = bankAtmService.addBankAtm(new BankAtm( "atm_1", AtmStatus.WORKING, bankOffice,
                "next to exit", employee, true, true, bankOffice.getMoneyAmount(), BigDecimal.TEN));

        UserService userService = UserServiceImpl.getInstance();
        var user = userService.addUser(new User("Ivanov Ivan Ivanovich", LocalDate.now(),
                BigDecimal.valueOf(random.nextDouble()*9_999 +1), "job", bank));

        PaymentAccountService paymentAccountService =PaymentAccountServiceImpl.getInstance();
        var paymentAccount = paymentAccountService.addPaymentAccount(new PaymentAccount(user, bank.getName()));

        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();
        var creditAccount = creditAccountService.addCreditAccount(new CreditAccount(user,
                bank.getName(), LocalDate.now(), 12, BigDecimal.valueOf(1000L),
                bank.getInterestRate(), employee,
                paymentAccount));

        System.out.println(bank);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(user);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);

    }

}
