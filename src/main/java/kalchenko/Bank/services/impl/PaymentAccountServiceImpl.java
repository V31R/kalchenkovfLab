package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.PaymentAccount;
import kalchenko.Bank.repositories.PaymentAccountRepository;
import kalchenko.Bank.services.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    PaymentAccountRepository paymentAccountRepository = new PaymentAccountRepository();

    public PaymentAccountServiceImpl() {}

    @Override
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {

        if(paymentAccountRepository.add(paymentAccount)){

            return paymentAccountRepository.getPaymentAccount();

        }

        return null;
    }

    @Override
    public PaymentAccount getPaymentAccount() {
        return paymentAccountRepository.getPaymentAccount();
    }

}
