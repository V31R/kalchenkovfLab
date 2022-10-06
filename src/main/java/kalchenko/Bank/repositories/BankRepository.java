package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.Bank;

public class BankRepository {

    Bank bank = null;

    BankRepository(){}

    public boolean add(Bank bank){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new Bank(bank);

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

    public Bank findById(Long id){
        if(this.bank == null || !this.bank.getId().equals(id)){

            return null;

        }

        return this.bank;
    }

    public boolean update(Bank bank){
        if(this.bank == null || !this.bank.getId().equals(bank.getId())){

            return false;

        }

        this.bank = bank;
        return true;
    }

}
