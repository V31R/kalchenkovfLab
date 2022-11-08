package kalchenko.bank.services.impl;

import kalchenko.bank.entity.User;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.BankService;
import kalchenko.bank.services.UserService;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс-одиночка
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl INSTANCE;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserServiceImpl();
        }

        return INSTANCE;
    }

    private final UserRepository userRepository = UserRepository.getInstance();
    private final BankService bankService = BankServiceImpl.getInstance();

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        var user = userRepository.findById(id);

        if (user != null) {
            userRepository.deleteById(id);
            var banks = user.getBanks();
            for (var bank : banks) {
                var tempBank = bankService.getBankById(bank.getId());
                tempBank.setUserNumber(tempBank.getUserNumber() - 1);
                bankService.update(tempBank);
            }

            return true;
        }

        return false;

    }

    @Override
    public User addUser(User user) {

        if (user.getCreditRate() < 100) {

            int rate = 100;
            BigDecimal value = BigDecimal.valueOf(1000L);
            while (user.getSalary().compareTo(value) == 1) {
                rate += 100;
                value = value.add(BigDecimal.valueOf(1000L));
            }
            user.setCreditRate(rate);
        }

        var newUser = userRepository.add(user);

        var banks = user.getBanks();
        for (var bank : banks) {
            var tempBank = bankService.getBankById(bank.getId());
            tempBank.setUserNumber(tempBank.getUserNumber() + 1);
            bankService.update(tempBank);
        }

        return newUser;

    }

    @Override
    public void outputUserAccounts(Long userId, OutputStream outputStream) {

        PrintStream printStream = new PrintStream(outputStream);

        printStream.printf("User data about %s\n", userRepository.findById(userId).getFullName());

        PaymentAccountRepository paymentAccountRepository = PaymentAccountRepository.getInstance();
        CreditAccountRepository creditAccountRepository = CreditAccountRepository.getInstance();
        var paymentAccounts = paymentAccountRepository.findAll().stream()
                .filter(paymentAccount -> paymentAccount.getUser().getId().compareTo(userId) == 0)
                .toList();
        if (paymentAccounts.size() > 0) {
            printStream.println("Payment accounts:");
            for (var paymentAccount : paymentAccounts) {
                printStream.println(paymentAccount);

            }
        } else {
            printStream.println("User does not have payment accounts");
        }

        var creditAccounts = creditAccountRepository.findAll().stream()
                .filter(creditAccount -> creditAccount.getUser().getId().compareTo(userId) == 0)
                .toList();

        if (creditAccounts.size() > 0) {
            printStream.println("Credit accounts:");
            for (var creditAccount : creditAccounts) {

                printStream.println(creditAccount);

            }
        } else {
            printStream.println("User does not have credit accounts");
        }

    }
}
