package kalchenko.bank.services.impl;

import kalchenko.bank.entity.*;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.ZeroMonthException;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.services.CreditAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Component
public class CreditAccountServiceImpl implements CreditAccountService {

    @Autowired
    private CreditAccountRepository creditAccountRepository;

    public CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee, BigDecimal sum, int monthNumber) {

        return new CreditAccount(user, bank.getName(), LocalDate.now(), monthNumber, sum,
                bank.getInterestRate(), employee, paymentAccount);
    }

    @Override
    public CreditAccount addCreditAccount(CreditAccount creditAccount) {
        if(creditAccount.getMonthNumber() <= 0){
            throw new ZeroMonthException();
        }
        creditAccount.setEnd(creditAccount.getStart().plusMonths(creditAccount.getMonthNumber()));

        var monthPayment = creditAccount.getSum()
                .multiply(BigDecimal.ONE.add(creditAccount.getInterestRate().divide(BigDecimal.valueOf(100L), RoundingMode.DOWN)))
                .divide(BigDecimal.valueOf(creditAccount.getMonthNumber()), RoundingMode.DOWN);

        creditAccount.setMonthPayment(monthPayment);

        return creditAccountRepository.add(creditAccount);

    }

    @Override
    public CreditAccount getCreditAccountById(Long id) {
        var creditAccount = creditAccountRepository.findById(id);
        if(creditAccount == null){
            throw new IdException();
        }
        return creditAccount;
    }

    @Override
    public List<CreditAccount> getAllCreditAccount() {
        return creditAccountRepository.findAll();
    }


    @Override
    public List<CreditAccount> getAllCreditAccountsByBank(Bank bank) {
        return creditAccountRepository.findAll()
                .stream()
                .filter(account -> account.getBankName().equals(bank.getName()))
                .toList();
    }

}
