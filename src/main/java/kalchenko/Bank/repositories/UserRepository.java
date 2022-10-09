package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.User;

public class UserRepository {

    User bank = null;

    public UserRepository(){}

    public boolean add(User user){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new User(user);

        }

        return isEmpty;
    }


    public boolean delete(){
        if(this.bank == null){

            return false;

        }

        this.bank = null;
        return true;
    }

    public User getUser(){
        return this.bank;
    }

    public boolean update(User user){
        if(this.bank == null){

            return false;

        }

        this.bank = user;
        return true;
    }

}
