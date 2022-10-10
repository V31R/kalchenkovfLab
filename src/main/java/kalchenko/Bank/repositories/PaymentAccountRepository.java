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


    public boolean delete(){
        if(this.bank == null){

            return false;

        }

        this.bank = null;
        return true;
    }

    public PaymentAccount getPaymentAccount(){
        if(this.bank == null){

            return null;

        }

        return this.bank;
    }

    public boolean update(PaymentAccount paymentAccount){
        if(this.bank == null){

            return false;

        }

        this.bank = paymentAccount;
        return true;
    }

}
