package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.BankAtm;

public class BankAtmRepository {

    BankAtm bankAtm = null;

    public BankAtmRepository(){}

    public boolean add(BankAtm bankAtm){
        var isEmpty = this.bankAtm == null;

        if (isEmpty){

            this.bankAtm = new BankAtm(bankAtm);

        }

        return isEmpty;
    }


    public boolean delete(){
        if(this.bankAtm == null){

            return false;

        }

        this.bankAtm = null;
        return true;
    }

    public BankAtm findAll(){
        return this.bankAtm;
    }

    public boolean update(BankAtm bankAtm){
        if(this.bankAtm == null){

            return false;

        }

        this.bankAtm = bankAtm;
        return true;
    }

}
