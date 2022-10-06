package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.BankAtm;

public class BankAtmRepository {

    BankAtm bankAtm = null;

    BankAtmRepository(){}

    public boolean add(BankAtm bankAtm){
        var isEmpty = this.bankAtm == null;

        if (isEmpty){

            this.bankAtm = new BankAtm(bankAtm);

        }

        return isEmpty;
    }


    public boolean deleteById(Long id){
        if(this.bankAtm == null || !this.bankAtm.getId().equals(id)){

            return false;

        }

        this.bankAtm = null;
        return true;
    }

    public BankAtm findById(Long id){
        if(this.bankAtm == null || !this.bankAtm.getId().equals(id)){

            return null;

        }

        return this.bankAtm;
    }

    public boolean update(BankAtm bankAtm){
        if(this.bankAtm == null || !this.bankAtm.getId().equals(bankAtm.getId())){

            return false;

        }

        this.bankAtm = bankAtm;
        return true;
    }

}
