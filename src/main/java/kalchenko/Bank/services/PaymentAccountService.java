package kalchenko.Bank.services;

import kalchenko.Bank.entity.PaymentAccount;

public interface PaymentAccountService {

    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount);
    public PaymentAccount getPaymentAccount();

}
