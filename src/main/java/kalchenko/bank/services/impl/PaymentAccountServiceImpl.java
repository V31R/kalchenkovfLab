package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.entity.User;
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

    public PaymentAccount createPaymentAccount(Bank bank, User user) {
        return new PaymentAccount(user, bank.getName());
    }

    @Override
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {

        var bank = BankServiceImpl.getInstance().getAllBanks()
                .stream()
                .filter(b -> b.getName().equals(paymentAccount.getBankName()))
                .findFirst();

        var userHasBank = paymentAccount.getUser().getBanks().contains(bank.get());

        if(!userHasBank){
            var user = paymentAccount.getUser();
            user.addBank(bank.get());
            UserServiceImpl.getInstance().updateUser(user);
        }

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
