package kalchenko.bank.repositories;

import kalchenko.bank.entity.User;

import java.util.List;

/**
 * Класс-одиночка
 */
public class UserRepository {

    private static UserRepository INSTANCE;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет user в репозиторий и возвращает добавленный объект,
     * если user не был равен null, иначе возвращает null.
     */
    public User add(User user) {

        return (User) repository.add(user);

    }

    /**
     * Удаляет пользователя по id.
     */
    public boolean deleteById(Long id) {

        return repository.deleteById(id);

    }

    /**
     * Возвращает клиента по id, который хранится в репозитории.
     */
    public User findById(Long id) {

        return (User) repository.findById(id);

    }

    /**
     * Возвращает список клиентов, которые хранятся в репозитории.
     */
    public List<User> findAll() {

        return repository.findAll().stream().map(entity -> (User) entity).toList();

    }


    /**
     * Обновляет пользователя.
     */
    public User update(User user) {
        return (User) repository.update(user);
    }

}
