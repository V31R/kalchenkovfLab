package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.PaymentAccount;
import kalchenko.bank.entity.User;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentAccountServiceImpl implements PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    public PaymentAccount createPaymentAccount(Bank bank, User user) {
        return new PaymentAccount(user, bank.getName());
    }

    @Override
    public PaymentAccount addPaymentAccount(PaymentAccount paymentAccount) {

        var bank = bankRepository.findAll()
                .stream()
                .filter(b -> b.getName().equals(paymentAccount.getBankName()))
                .findFirst();

        var userHasBank = paymentAccount.getUser().getBanks().contains(bank.get());

        if(!userHasBank){
            var user = paymentAccount.getUser();
            user.addBank(bank.get());
            userRepository.update(user);
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

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByBank(Bank bank) {
        return paymentAccountRepository.findAll()
                .stream()
                .filter(account -> account.getBankName().equals(bank.getName()))
                .toList();
    }
}
