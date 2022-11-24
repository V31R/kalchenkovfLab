package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.entity.User;
import kalchenko.bank.exceptions.IdException;
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
    public PaymentAccount paymentAccountRegistration(Bank bank, User user) {

        if(!user.getBanks().contains(bank)){

            return this.addPaymentAccount(this.createPaymentAccount(bank, user));

        }else {
            return this.getAllPaymentAccountByBankNameAndUser(bank.getName(), user.getId())
                    .stream()
                    .findFirst().get();
        }
    }

    @Override
    public PaymentAccount getPaymentAccountById(Long id) throws IdException {
        var paymentAccount = paymentAccountRepository.findById(id);
        if(paymentAccount == null){
            throw new IdException();
        }
        return paymentAccount;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccount() {
        return paymentAccountRepository.findAll();
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountByBankNameAndUser(String bankName, Long userId) {
        return paymentAccountRepository.findAll()
                .stream()
                .filter(account -> account.getBankName().equals(bankName) && account.getUser().getId().compareTo(userId) == 0)
                .toList();
    }
}
