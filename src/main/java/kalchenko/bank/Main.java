package kalchenko.bank;

import kalchenko.bank.entity.*;
import kalchenko.bank.services.*;
import kalchenko.bank.services.impl.*;
import kalchenko.bank.utils.AtmStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Random;

public class Main {

    private static Random random = new Random();
    private static long number = 0L;
    public static void main(String[] argv){

        BankService bankService = BankServiceImpl.getInstance();
        var bank = bankService.addBank(createBank());

        BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();
        var  bankOffice =bankOfficeService.addBankOffice(createBankOffice(bank));

        EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        var employee =employeeService.addEmployee(createEmployee(bankOffice));

        BankAtmService bankAtmService = BankAtmServiceImpl.getInstance();
        var bankAtm = bankAtmService.addBankAtm(createBankAtm(bankOffice, employee));

        UserService userService = UserServiceImpl.getInstance();
        var user = userService.addUser(createUser(bank));

        PaymentAccountService paymentAccountService =PaymentAccountServiceImpl.getInstance();
        var paymentAccount = paymentAccountService.addPaymentAccount(createPaymentAccount(bank, user));

        CreditAccountService creditAccountService = CreditAccountServiceImpl.getInstance();
        var creditAccount = creditAccountService.addCreditAccount(createCreditAccount(bank, user, paymentAccount, employee));

        System.out.println(bank);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(user);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);

        //userService.outputUserAccounts(user.getId(), System.out);
        bankService.outputBankInfo(bank.getId(), System.out);
        userService.outputUserAccounts(user.getId(), System.out);

    }

    static Bank createBank(){
        var rate =  random.nextInt(100);
        BigDecimal interestRate =  BigDecimal.valueOf(random.nextDouble()*(20.d - rate/10.d));
        BigDecimal money = BigDecimal.valueOf(random.nextInt(1_000_000));
        return new Bank(String.format("Bank_%d", number++), rate, money, interestRate );
    }

    static BankOffice createBankOffice(Bank bank){
        var n = number++;

        return new BankOffice(String.format("Office_%d", n),String.format("Address_%d", n), bank, true, true,
                true, true, true, bank.getMoneyAmount(), BigDecimal.valueOf(10.5d));
    }

    static Employee createEmployee(BankOffice bankOffice){
        return new Employee(String.format("Employee_name_%d",  number++), LocalDate.now(), "job", true, bankOffice,
                true, BigDecimal.ONE);
    }

    static BankAtm createBankAtm(BankOffice bankOffice, Employee employee){
        return new BankAtm( String.format("Atm_%d", number++), AtmStatus.WORKING, bankOffice, "next to exit",
                employee, true, true, bankOffice.getMoneyAmount(), BigDecimal.TEN);
    }


    static User createUser(Bank bank){
        return new User(String.format("User_name_%d",  number++), LocalDate.now(),
                BigDecimal.valueOf(random.nextDouble()*9_999 +1), "job", bank);
    }

    static PaymentAccount createPaymentAccount(Bank bank, User user){
        return new PaymentAccount(user, bank.getName());
    }


    static CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee){
        return new CreditAccount(user, bank.getName(), LocalDate.now(), 12, BigDecimal.valueOf(1000L),
                bank.getInterestRate(), employee, paymentAccount);
    }

}
