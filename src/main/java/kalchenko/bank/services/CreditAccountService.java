package kalchenko.bank.services;

import kalchenko.bank.entity.CreditAccount;

public interface CreditAccountService {

    /**
     * Добавляет creditAccount в репозиторий.
     * При добавлении вычисляет дату окончания кредита и ежемесячный платёж.
     */
    CreditAccount addCreditAccount(CreditAccount creditAccount);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    CreditAccount getCreditAccount();

}
