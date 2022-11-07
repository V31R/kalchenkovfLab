package kalchenko.bank.repositories;

import kalchenko.bank.entity.User;

import java.util.List;

public class UserRepository {

    private static UserRepository INSTANCE;

    private UserRepository(){}

    public static UserRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет user в репозиторий и возвращает добавленный объект,
     * если user не был равен null, иначе возвращает null.
     */
    public User add(User user){

        return (User) repository.add(user);

    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean deleteById(Long id){

        return repository.deleteById(id);

    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public User findById(Long id){

        return (User) repository.findById(id);

    }

    /**
     * Возвращает список клиентов, которые хранятся в репозитории.
     */
    public List<User> findAll(){

        return repository.findAll().stream().map(entity -> (User) entity).toList();

    }


    /**
     * Если объект существует, то обновляет его и возвращает его,
     * иначе возвращает null.
     */
    public User update(User user){
        return (User) repository.update(user);
    }

}
