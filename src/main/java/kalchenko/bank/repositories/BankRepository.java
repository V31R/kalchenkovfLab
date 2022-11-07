package kalchenko.bank.repositories;

import kalchenko.bank.entity.Bank;

/**
 *  Класс-одиночка
 */
public class BankRepository {

    private static BankRepository INSTANCE;

    private BankRepository(){}

    public static BankRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankRepository();
        }

        return INSTANCE;
    }

    private Bank bank = null;

    /**
     * Если до этого там не находилось другого объекта Bank
     * добавляет bank в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public boolean add(Bank bank){
        var isEmpty = this.bank == null;

        if (isEmpty && bank != null){

            this.bank = new Bank(bank);

        }

        return isEmpty;
    }

    /**
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

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public Bank getBank(){
        return this.bank;
    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(Bank bank){
        if(this.bank == null && bank != null){

            return false;

        }

        this.bank = bank;
        return true;
    }

}
