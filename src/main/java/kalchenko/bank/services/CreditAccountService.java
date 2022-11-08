package kalchenko.bank.services;

import kalchenko.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {

    /**
     * Добавляет creditAccount в репозиторий.
     * При добавлении вычисляет дату окончания кредита и ежемесячный платёж.
     */
    CreditAccount addCreditAccount(CreditAccount creditAccount);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    CreditAccount getCreditAccountById(Long id);

    /**
     * Возвращает все кредиты, которые хранятся в репозитории.
     */
    List<CreditAccount> getAllCreditAccount();
}
