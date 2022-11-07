package kalchenko.bank.services.impl;

import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.services.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private static PaymentAccountServiceImpl INSTANCE;

    private PaymentAccountServiceImpl(){}

    public static PaymentAccountServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaymentAccountServiceImpl();
        }

        return INSTANCE;
    }

    private PaymentAccountRepository paymentAccountRepository = PaymentAccountRepository.getInstance();

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
