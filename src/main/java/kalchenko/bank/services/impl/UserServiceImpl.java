package kalchenko.bank.services.impl;

import kalchenko.bank.entity.User;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.BankService;
import kalchenko.bank.services.UserService;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl INSTANCE;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        if(INSTANCE == null) {
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

        if(user!= null){
            userRepository.deleteById(id);
            var banks = user.getBanks();
            for(var bank : banks){
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

        if(user.getCreditRate() < 100){

            int rate = 100;
            BigDecimal value = BigDecimal.valueOf(1000L);
            while(user.getSalary().compareTo(value) == 1){
                rate+=100;
                value=value.add(BigDecimal.valueOf(1000L));
            }
            user.setCreditRate(rate);
        }

        var newUser= userRepository.add(user);

        var banks = user.getBanks();
        for(var bank : banks){
            var tempBank = bankService.getBankById(bank.getId());
            tempBank.setUserNumber(tempBank.getUserNumber() + 1);
            bankService.update(tempBank);
        }

        return newUser;

    }
}
