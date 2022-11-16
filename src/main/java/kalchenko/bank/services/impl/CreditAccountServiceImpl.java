package kalchenko.bank.services.impl;

import kalchenko.bank.entity.*;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.services.CreditAccountService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс-одиночка
 */
public class CreditAccountServiceImpl implements CreditAccountService {

    private static CreditAccountServiceImpl INSTANCE;

    private CreditAccountServiceImpl() {
    }

    public static CreditAccountServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreditAccountServiceImpl();
        }

        return INSTANCE;
    }

    private final CreditAccountRepository creditAccountRepository = CreditAccountRepository.getInstance();

    public CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee) {
        final int monthNumber = 12;
        final BigDecimal creditSum = BigDecimal.valueOf(1000L);

        return new CreditAccount(user, bank.getName(), LocalDate.now(), monthNumber, creditSum,
                bank.getInterestRate(), employee, paymentAccount);
    }

    @Override
    public CreditAccount addCreditAccount(CreditAccount creditAccount) {

        creditAccount.setEnd(creditAccount.getStart().plusMonths(creditAccount.getMonthNumber()));

        var monthPayment = creditAccount.getSum()
                .multiply(BigDecimal.ONE.add(creditAccount.getInterestRate().divide(BigDecimal.valueOf(100L), RoundingMode.DOWN)))
                .divide(BigDecimal.valueOf(creditAccount.getMonthNumber()), RoundingMode.DOWN);

        creditAccount.setMonthPayment(monthPayment);

        return creditAccountRepository.add(creditAccount);

    }

    @Override
    public CreditAccount getCreditAccountById(Long id) {
        return creditAccountRepository.findById(id);
    }

    @Override
    public List<CreditAccount> getAllCreditAccount() {
        return creditAccountRepository.findAll();
    }
}
