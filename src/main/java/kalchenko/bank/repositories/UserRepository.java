package kalchenko.bank.repositories;

import kalchenko.bank.entity.User;

public class UserRepository {

    private User bank = null;

    public UserRepository(){}

    /**
     * Если до этого там не находилось другого объекта User
     * добавляет user в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public boolean add(User user){
        var isEmpty = this.bank == null;

        if (isEmpty && user != null){

            this.bank = new User(user);

        }

        return isEmpty;
    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.bank == null){
            return false;
        }

        this.bank = null;
        return true;
    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public User getUser(){
        return this.bank;
    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(User user){
        if(this.bank == null && user != null){

            return false;

        }

        this.bank = user;
        return true;
    }

}
