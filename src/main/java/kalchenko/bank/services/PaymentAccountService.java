package kalchenko.bank.services;

import kalchenko.bank.entity.PaymentAccount;

import java.util.List;

public interface PaymentAccountService {

    /**
     * Добавляет paymentAccount в репозиторий.
     */
    PaymentAccount addPaymentAccount(PaymentAccount paymentAccount);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    PaymentAccount getPaymentAccountById(Long id);

    /**
     * Возвращает всех пользователей, которые хранятся в репозитории.
     */
    List<PaymentAccount> getAllPaymentAccount();

}
