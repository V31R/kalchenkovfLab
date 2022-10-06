package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.BankOffice;

public class BankOfficeRepository {

    BankOffice bankOffice = null;

    BankOfficeRepository(){}

    public boolean add(BankOffice bankOffice){
        var isEmpty = this.bankOffice == null;

        if (isEmpty){

            this.bankOffice = new BankOffice(bankOffice);

        }

        return isEmpty;
    }


    public boolean deleteById(Long id){
        if(this.bankOffice == null || !this.bankOffice.getId().equals(id)){

            return false;

        }

        this.bankOffice = null;
        return true;
    }

    public BankOffice findById(Long id){
        if(this.bankOffice == null || !this.bankOffice.getId().equals(id)){

            return null;

        }

        return this.bankOffice;
    }

    public boolean update(BankOffice bankOffice){
        if(this.bankOffice == null || !this.bankOffice.getId().equals(bankOffice.getId())){

            return false;

        }

        this.bankOffice = bankOffice;
        return true;
    }

}
