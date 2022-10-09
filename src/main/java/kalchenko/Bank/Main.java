package kalchenko.Bank;


import kalchenko.Bank.entity.Bank;
import kalchenko.Bank.entity.BankOffice;
import kalchenko.Bank.entity.Employee;
import kalchenko.Bank.services.impl.BankOfficeServiceImpl;
import kalchenko.Bank.services.impl.BankServiceImpl;
import kalchenko.Bank.services.impl.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] argv){
        Random random = new Random();

        BankServiceImpl bankService = new BankServiceImpl();
        var rate =  random.nextInt(100);
        BigDecimal interestRate =  BigDecimal.valueOf(random.nextDouble()*(20.d - rate/10.d));
        BigDecimal money = BigDecimal.valueOf(random.nextInt(1_000_000));
        bankService.addBank(new Bank(1L, "Bank_1", rate, money, interestRate ));

        BankOfficeServiceImpl bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankOfficeService.addBankOffice(new BankOffice(1L,"Office_1","Address_1",
                bankService.getBank(), true, true, true,
                true, true,bankService.getBank().getMoneyAmount(), BigDecimal.valueOf(10.5d)));

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(bankOfficeService);
        employeeService.addEmployee(new Employee(1L, "Ivanov Ivan Ivanovich", new Date(), "job",
                true, bankOfficeService.getBankOffice(), true, BigDecimal.ONE));

        System.out.println(bankService.getBank());
        System.out.println(bankOfficeService.getBankOffice());
        System.out.println(employeeService.getEmployee());

    }

}
