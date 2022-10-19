package kalchenko.bank.services.impl;

import kalchenko.bank.entity.CreditAccount;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.services.CreditAccountService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

public class CreditAccountServiceImpl implements CreditAccountService {

    private CreditAccountRepository creditAccountRepository = new CreditAccountRepository();

    public CreditAccountServiceImpl() {}

    @Override
    public CreditAccount addCreditAccount(CreditAccount creditAccount) {

        creditAccount.setEnd(creditAccount.getStart().plusMonths(creditAccount.getMonthNumber()));

        var monthPayment = creditAccount.getSum()
                .multiply(BigDecimal.ONE.add(creditAccount.getInterestRate().divide(BigDecimal.valueOf(100L), RoundingMode.DOWN)))
                .divide(BigDecimal.valueOf(creditAccount.getMonthNumber()), RoundingMode.DOWN);

        creditAccount.setMonthPayment(monthPayment);

        if(creditAccountRepository.add(creditAccount)){

            return creditAccountRepository.getCreditAccount();

        }

        return null;
    }

    @Override
    public CreditAccount getCreditAccount() {
        return creditAccountRepository.getCreditAccount();
    }
}
