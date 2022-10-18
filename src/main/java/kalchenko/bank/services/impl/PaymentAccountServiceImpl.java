package kalchenko.bank.services.impl;

import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.services.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private PaymentAccountRepository paymentAccountRepository = new PaymentAccountRepository();

    public PaymentAccountServiceImpl() {}

    /*
     * Добавляет paymentAccount в репозиторий.
     */
    @Override
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {

        if(paymentAccountRepository.add(paymentAccount)){

            return paymentAccountRepository.getPaymentAccount();

        }

        return null;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public PaymentAccount getPaymentAccount() {
        return paymentAccountRepository.getPaymentAccount();
    }

}
