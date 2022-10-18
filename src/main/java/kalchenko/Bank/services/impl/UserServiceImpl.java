package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.User;
import kalchenko.Bank.repositories.UserRepository;
import kalchenko.Bank.services.BankService;
import kalchenko.Bank.services.UserService;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepository();
    private BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public User getUser() {
        return userRepository.getUser();
    }

    /*
     * Удаляет объект, извещает об этом связанный Bank.
     */
    @Override
    public boolean deleteUser() {
        return userRepository.delete() && bankService.deleteUser();
    }

    /*
     * Добавляет user в репозиторий, извещает об этом связанный bank.
     * При добавлении вычисляет кредитный рейтинг пользователя,
     * если такового раньше не было.
     */
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
