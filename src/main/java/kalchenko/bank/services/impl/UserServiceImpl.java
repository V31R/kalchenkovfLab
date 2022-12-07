package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.User;
import kalchenko.bank.exceptions.*;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.repositories.CreditAccountRepository;
import kalchenko.bank.repositories.PaymentAccountRepository;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private CreditAccountRepository creditAccountRepository;

    private static int number = 0;
    private static final Random RANDOM = new Random();
    private static final int MAX_SALARY = 9_999;
    private static final int MIN_SALARY = 1;

    public  User createUser(Bank bank) {
        final int years = 18;
        return new User(String.format("User_name_%d", number++), LocalDate.now().minusYears(years),
                BigDecimal.valueOf(RANDOM.nextDouble() * MAX_SALARY + MIN_SALARY), "job", bank);
    }

    @Override
    public User getUserById(Long id) {
        var user = userRepository.findById(id);
        if(user == null){
            throw new IdException();
        }
        return user;
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
                var tempBank = bankRepository.findById(bank.getId());
                tempBank.setUserNumber(tempBank.getUserNumber() - 1);
                bankRepository.update(tempBank);
            }

            return true;
        }else{
            throw new NotExistedObjectException();
        }

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
            var tempBank = bankRepository.findById(bank.getId());
            tempBank.setUserNumber(tempBank.getUserNumber() + 1);
            bankRepository.update(tempBank);
        }

        return newUser;

    }

    @Override
    public User updateUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public void outputUserAccounts(Long userId, OutputStream outputStream) {

        PrintStream printStream = new PrintStream(outputStream);
        var user = userRepository.findById(userId);
        printStream.printf("User data about %s\n", user.getFullName());
        printStream.println(user);

        var paymentAccounts = paymentAccountRepository.findAll().stream()
                .filter(paymentAccount -> paymentAccount.getUser().getId().compareTo(userId) == 0)
                .toList();
        if (paymentAccounts.size() > 0) {
            printStream.println("Payment accounts:");
            paymentAccounts.forEach(printStream::println);
        } else {
            printStream.println("User does not have payment accounts");
        }

        var creditAccounts = creditAccountRepository.findAll().stream()
                .filter(creditAccount -> creditAccount.getUser().getId().compareTo(userId) == 0)
                .toList();

        if (creditAccounts.size() > 0) {
            printStream.println("Credit accounts:");
            creditAccounts.forEach(printStream::println);
        } else {
            printStream.println("User does not have credit accounts");
        }

    }

}
