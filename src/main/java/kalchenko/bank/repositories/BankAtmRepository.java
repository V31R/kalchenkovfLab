package kalchenko.bank.repositories;

import kalchenko.bank.entity.BankAtm;

public class BankAtmRepository {

    private BankAtm bankAtm = null;

    public BankAtmRepository(){}

    /**
    * Если до этого там не находилось другого объекта BankAtm
    * добавляет bankAtm в репозиторий и возвращает добавленный объект,
    * иначе возвращает null.
    */
    public boolean add(BankAtm bankAtm){
        var isEmpty = this.bankAtm == null;

        if (isEmpty && bankAtm != null){

            this.bankAtm = new BankAtm(bankAtm);

        }

        return isEmpty;
    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.bankAtm == null){
            return false;
        }

        this.bankAtm = null;
        return true;
    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public BankAtm getBankAtm(){
        return this.bankAtm;
    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(BankAtm bankAtm){
        if(this.bankAtm == null && bankAtm != null){

            return false;

        }

        this.bankAtm = bankAtm;
        return true;
    }

}
