package kalchenko.bank.services.impl;

import kalchenko.bank.entity.CreditAccount;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.services.CreditAccountService;

import java.math.BigDecimal;
import java.util.Calendar;

public class CreditAccountServiceImpl implements CreditAccountService {

    private CreditAccountRepository creditAccountRepository = new CreditAccountRepository();

    public CreditAccountServiceImpl() {}

    @Override
    public CreditAccount addCreditAccount(CreditAccount creditAccount) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creditAccount.getStart());
        calendar.add(Calendar.MONTH, creditAccount.getMonthNumber());
        creditAccount.setEnd(calendar.getTime());

        var monthPayment = creditAccount.getSum()
                .multiply(BigDecimal.ONE.add(creditAccount.getInterestRate().divide(BigDecimal.valueOf(100L))))
                .divide(BigDecimal.valueOf(creditAccount.getMonthNumber()));

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
