package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.CreditAccount;
import kalchenko.Bank.repositories.CreditAccountRepository;
import kalchenko.Bank.services.CreditAccountService;

import java.math.BigDecimal;
import java.util.Calendar;

public class CreditAccountServiceImpl implements CreditAccountService {

    CreditAccountRepository creditAccountRepository = new CreditAccountRepository();

    public CreditAccountServiceImpl() {}

    /*
     * Добавляет creditAccount в репозиторий.
     * При добавлении вычисляет дату окончания кредита и ежемесячный платёж.
     */
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

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public CreditAccount getCreditAccount() {
        return creditAccountRepository.getCreditAccount();
    }
}
