package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.PaymentAccount;

public class PaymentAccountRepository {

    PaymentAccount bank = null;

    public PaymentAccountRepository(){}

    /*
     * Если до этого там не находилось другого объекта PaymentAccount
     * добавляет paymentAccount в репозиторий и возвращает добаленный объект,
     * иначе возвращает null.
     */
    public boolean add(PaymentAccount paymentAccount){
        var isEmpty = this.bank == null;

        if (isEmpty){

            this.bank = new PaymentAccount(paymentAccount);

        }

        return isEmpty;
    }

    /*
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.bank == null){
            return false;
        }

        this.bank = null;
        return true;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    public PaymentAccount getPaymentAccount(){
        if(this.bank == null){

            return null;

        }

        return this.bank;
    }

    /*
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(PaymentAccount paymentAccount){
        if(this.bank == null){

            return false;

        }

        this.bank = paymentAccount;
        return true;
    }

}
