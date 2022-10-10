package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.CreditAccount;

public class CreditAccountRepository {


    CreditAccount creditAccount = null;

    public CreditAccountRepository(){}

    public boolean add(CreditAccount creditAccount){
        var isEmpty = this.creditAccount == null;

        if (isEmpty){

            this.creditAccount = new CreditAccount(creditAccount);

        }

        return isEmpty;
    }


    public boolean delete(){
        if(this.creditAccount == null){

            return false;

        }

        this.creditAccount = null;
        return true;
    }

    public CreditAccount getCreditAccount(){
        if(this.creditAccount == null ){

            return null;

        }

        return this.creditAccount;
    }

    public boolean update(CreditAccount creditAccount){
        if(this.creditAccount == null){

            return false;

        }

        this.creditAccount = creditAccount;
        return true;
    }


}
