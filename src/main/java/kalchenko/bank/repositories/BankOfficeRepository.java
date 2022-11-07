package kalchenko.bank.repositories;

import kalchenko.bank.entity.BankOffice;

public class BankOfficeRepository {

    private static BankOfficeRepository INSTANCE;

    private BankOfficeRepository(){}

    public static BankOfficeRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BankOfficeRepository();
        }

        return INSTANCE;
    }

    private BankOffice bankOffice = null;

    /**
     * Если до этого там не находилось другого объекта BankOffice
     * добавляет bankOffice в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public boolean add(BankOffice bankOffice){
        var isEmpty = this.bankOffice == null;

        if (isEmpty && bankOffice != null){

            this.bankOffice = new BankOffice(bankOffice);

        }

        return isEmpty;
    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.bankOffice == null){
            return false;
        }

        this.bankOffice = null;
        return true;
    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public BankOffice getBankOffice(){
        if(this.bankOffice == null){

            return null;

        }

        return this.bankOffice;
    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(BankOffice bankOffice){
        if(this.bankOffice == null && bankOffice != null){

            return false;

        }

        this.bankOffice = bankOffice;
        return true;
    }

}
