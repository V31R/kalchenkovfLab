package kalchenko.bank.services;

import kalchenko.bank.entity.PaymentAccount;

public interface PaymentAccountService {

    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount);
    public PaymentAccount getPaymentAccount();

}
