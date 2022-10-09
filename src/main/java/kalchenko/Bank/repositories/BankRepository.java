package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.Bank;

public class BankRepository {

    Bank bank = null;

    public BankRepository(){}

    public boolean add(Bank bank){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new Bank(bank);

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

    public Bank getBank(){
        return this.bank;
    }

    public boolean update(Bank bank){
        if(this.bank == null){

            return false;

        }

        this.bank = bank;
        return true;
    }

}
