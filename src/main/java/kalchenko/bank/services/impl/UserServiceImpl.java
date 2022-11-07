package kalchenko.bank.services.impl;

import kalchenko.bank.entity.User;
import kalchenko.bank.repositories.BankRepository;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.BankService;
import kalchenko.bank.services.UserService;

import java.math.BigDecimal;

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
    private final BankRepository bankRepository = BankRepository.getInstance();

    @Override
    public User getUser() {
        return userRepository.getUser();
    }

    @Override
    public boolean deleteUser() {
        var user = userRepository.getUser();

        if(user!= null){
            userRepository.delete();
            var bank = bankRepository.findById(user.getBank().getId());
            bank.setUserNumber(bank.getUserNumber() - 1);
            bankRepository.update(bank);
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

        if(userRepository.add(user)){
            var bank = bankRepository.findById(user.getBank().getId());

            if(bank != null){
                bank.setUserNumber(bank.getUserNumber() + 1);
                bankRepository.update(bank);
            }

            return userRepository.getUser();

        }

        return null;

    }
}
