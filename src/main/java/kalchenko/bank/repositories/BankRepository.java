package kalchenko.bank.repositories;

import kalchenko.bank.entity.Bank;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private final Map<Long, Bank> banks = new LinkedHashMap<>();
    private long currentId = 1L;

    /**
     * Если до этого там не находилось другого объекта Bank
     * добавляет bank в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public Bank add(Bank bank){

        if(bank == null){
            return null;
        }

        bank.setId(currentId++);
        this.banks.put(bank.getId(), bank);
        return bank;

    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean deleteById(Long id){
        if(!this.banks.containsKey(id)){
            return false;
        }

        banks.remove(id);
        return true;

    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public Bank findById(Long id){

        return this.banks.get(id);

    }

    /**
     * Возвращает список банков, которые хранятся в репозитории.
     */
    public List<Bank> findAll(){

        return this.banks.values().stream().toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(Bank bank){

        if(bank == null || !this.banks.containsKey(bank.getId())){
            return false;
        }

        this.banks.replace(bank.getId(), bank);
        return true;

    }

}
