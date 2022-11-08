package kalchenko.bank.services.impl;

import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.services.PaymentAccountService;

import java.util.List;

/**
 * Класс-одиночка
 */
public class PaymentAccountServiceImpl implements PaymentAccountService {

    private static PaymentAccountServiceImpl INSTANCE;

    private PaymentAccountServiceImpl() {
    }

    public static PaymentAccountServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PaymentAccountServiceImpl();
        }

        return INSTANCE;
    }

    private final PaymentAccountRepository paymentAccountRepository = PaymentAccountRepository.getInstance();

    @Override
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {

        return paymentAccountRepository.add(paymentAccount);

    }

    @Override
    public PaymentAccount getPaymentAccountById(Long id) {
        return paymentAccountRepository.findById(id);
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccount() {
        return paymentAccountRepository.findAll();
    }
}
