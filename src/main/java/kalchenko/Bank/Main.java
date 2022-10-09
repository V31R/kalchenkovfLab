package kalchenko.Bank;


import kalchenko.Bank.entity.Bank;
import kalchenko.Bank.entity.BankOffice;
import kalchenko.Bank.services.impl.BankOfficeServiceImpl;
import kalchenko.Bank.services.impl.BankServiceImpl;

import java.math.BigDecimal;
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

        System.out.println(bankService.getBank());
        System.out.println(bankOfficeService.getBankOffice());

    }

}
