package kalchenko.bank.services;

import kalchenko.bank.entity.Bank;
import kalchenko.bank.entity.User;
import kalchenko.bank.exceptions.*;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    /**
     * Добавляет user в репозиторий, извещает об этом связанный bank.
     * При добавлении вычисляет кредитный рейтинг пользователя,
     * если такового раньше не было.
     */
    User addUser(User user) throws IdException;

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    User getUserById(Long id) throws IdException;

    /**
     * Возвращает всех пользователей, которые хранятся в репозитории.
     */
    List<User> getAllUsers();

    /**
     * Удаляет объект, извещает об этом связанный Bank.
     */
    boolean deleteUser(Long id) throws NotExistedObjectException, IdException;

    /**
     * Создаёт клиента
     */
    User createUser(Bank bank);

    User updateUser(User user);

    /**
     * Выводит пользователя с userId в поток outputStream
     */
    void outputUserAccounts(Long userId, OutputStream outputStream);


    /**
     * Выдаёт кредит пользователю
     * @param userId id пользователя, которому будет выдан кредит
     * @param creditSum сумма, на которую будет выдан кредит
     * @return возвращает id кредита, если он был выдан, иначе возвращает null.
     */
    Long getCredit(Long userId, BigDecimal creditSum) throws NotExistedObjectException, LendingTermsException, NegativeSumException, IdException, ZeroMonthException;

}
