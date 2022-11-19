package kalchenko.bank.services.impl;

import kalchenko.bank.entity.*;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.ZeroMonthException;
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

    public CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee, BigDecimal sum, int monthNumber) {

        return new CreditAccount(user, bank.getName(), LocalDate.now(), monthNumber, sum,
                bank.getInterestRate(), employee, paymentAccount);
    }

    @Override
    public CreditAccount addCreditAccount(CreditAccount creditAccount) throws ZeroMonthException {
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
    public CreditAccount getCreditAccountById(Long id) throws IdException {
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
}
