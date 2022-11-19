package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.entity.User;
import kalchenko.bank.exceptions.IdException;

import java.util.List;

public interface PaymentAccountService {

    /**
     * Добавляет paymentAccount в репозиторий.
     */
    PaymentAccount addPaymentAccount(PaymentAccount paymentAccount);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    PaymentAccount getPaymentAccountById(Long id) throws IdException;

    /**
     * Возвращает все платёжные счета, которые хранятся в репозитории.
     */
    List<PaymentAccount> getAllPaymentAccount();

    /**
     * Создаёт счёт
     */
    PaymentAccount createPaymentAccount(Bank bank, User user);

}
