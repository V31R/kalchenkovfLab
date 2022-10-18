package kalchenko.bank.services;

import kalchenko.bank.entity.PaymentAccount;

public interface PaymentAccountService {

    /**
     * Добавляет paymentAccount в репозиторий.
     */
    PaymentAccount addPaymentAccount(PaymentAccount paymentAccount);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    PaymentAccount getPaymentAccount();

}
