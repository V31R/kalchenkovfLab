package kalchenko.bank.services.impl;

import kalchenko.bank.entity.User;
import kalchenko.bank.repositories.UserRepository;
import kalchenko.bank.services.BankService;
import kalchenko.bank.services.UserService;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepository();
    private BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public User getUser() {
        return userRepository.getUser();
    }

    @Override
    public boolean deleteUser() {
        return userRepository.delete() && bankService.deleteUser();
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

            bankService.addUser();
            return userRepository.getUser();

        }

        return null;

    }
}
