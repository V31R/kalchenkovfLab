package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.User;

public class UserRepository {

    User bank = null;

    UserRepository(){}

    public boolean add(User user){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new User(user);

        }

        return isEmpty;
    }


    public boolean deleteById(Long id){
        if(this.bank == null || !this.bank.getId().equals(id)){

            return false;

        }

        this.bank = null;
        return true;
    }

    public User findById(Long id){
        if(this.bank == null || !this.bank.getId().equals(id)){

            return null;

        }

        return this.bank;
    }

    public boolean update(User user){
        if(this.bank == null || !this.bank.getId().equals(user.getId())){

            return false;

        }

        this.bank = user;
        return true;
    }

}
