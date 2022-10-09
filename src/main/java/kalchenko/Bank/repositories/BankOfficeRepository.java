package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.BankOffice;

public class BankOfficeRepository {

    BankOffice bankOffice = null;

    public BankOfficeRepository(){}

    public boolean add(BankOffice bankOffice){
        var isEmpty = this.bankOffice == null;

        if (isEmpty){

            this.bankOffice = new BankOffice(bankOffice);

        }

        return isEmpty;
    }


    public boolean delete(){
        if(this.bankOffice == null){

            return false;

        }

        this.bankOffice = null;
        return true;
    }

    public BankOffice getBankOffice(){
        if(this.bankOffice == null){

            return null;

        }

        return this.bankOffice;
    }

    public boolean update(BankOffice bankOffice){
        if(this.bankOffice == null){

            return false;

        }

        this.bankOffice = bankOffice;
        return true;
    }

}
