package kalchenko.bank.services;

import kalchenko.bank.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Добавляет user в репозиторий, извещает об этом связанный bank.
     * При добавлении вычисляет кредитный рейтинг пользователя,
     * если такового раньше не было.
     */
    User addUser(User user);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    User getUserById(Long id);

    /**
     * Возвращает всех пользователей, которые хранятся в репозитории.
     */
    List<User> getAllUsers();

    /**
     * Удаляет объект, извещает об этом связанный Bank.
     */
    boolean deleteUser(Long id);

}
