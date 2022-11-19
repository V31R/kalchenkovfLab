package kalchenko.bank.services;

import kalchenko.bank.entity.*;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.ZeroMonthException;

import java.math.BigDecimal;
import java.util.List;

public interface CreditAccountService {

    /**
     * Добавляет creditAccount в репозиторий.
     * При добавлении вычисляет дату окончания кредита и ежемесячный платёж.
     */
    CreditAccount addCreditAccount(CreditAccount creditAccount) throws ZeroMonthException;

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    CreditAccount getCreditAccountById(Long id) throws IdException;

    /**
     * Возвращает все кредиты, которые хранятся в репозитории.
     */
    List<CreditAccount> getAllCreditAccount();

    /**
     * Создаёт кредитичный счёт
     */
    CreditAccount createCreditAccount(Bank bank, User user, PaymentAccount paymentAccount, Employee employee, BigDecimal sum, int monthNumber);
}
