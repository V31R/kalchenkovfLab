package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.CreditAccount;

public class CreditAccountRepository {


    CreditAccount creditAccount = null;

    public CreditAccountRepository(){}

    /*
     * Если до этого там не находилось другого объекта CreditAccount
     * добавляет creditAccount в репозиторий и возвращает добаленный объект,
     * иначе возвращает null.
     */
    public boolean add(CreditAccount creditAccount){
        var isEmpty = this.creditAccount == null;

        if (isEmpty){

            this.creditAccount = new CreditAccount(creditAccount);

        }

        return isEmpty;
    }

    /*
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.creditAccount == null){

            return false;

        }

        this.creditAccount = null;
        return true;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    public CreditAccount getCreditAccount(){
        if(this.creditAccount == null ){

            return null;

        }

        return this.creditAccount;
    }

    /*
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(CreditAccount creditAccount){
        if(this.creditAccount == null){

            return false;

        }

        this.creditAccount = creditAccount;
        return true;
    }


}
