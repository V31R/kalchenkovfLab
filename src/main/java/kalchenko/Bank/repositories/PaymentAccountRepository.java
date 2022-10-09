package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.PaymentAccount;

public class PaymentAccountRepository {

    PaymentAccount bank = null;

    public PaymentAccountRepository(){}

    public boolean add(PaymentAccount paymentAccount){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new PaymentAccount(paymentAccount);

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

    public PaymentAccount findById(Long id){
        if(this.bank == null || !this.bank.getId().equals(id)){

            return null;

        }

        return this.bank;
    }

    public boolean update(PaymentAccount paymentAccount){
        if(this.bank == null || !this.bank.getId().equals(paymentAccount.getId())){

            return false;

        }

        this.bank = paymentAccount;
        return true;
    }

}
