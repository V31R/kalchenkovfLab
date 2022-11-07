package kalchenko.bank.repositories;

import kalchenko.bank.entity.CreditAccount;

import java.util.List;

public class CreditAccountRepository {

    private static CreditAccountRepository INSTANCE;

    private CreditAccountRepository(){}

    public static CreditAccountRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CreditAccountRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();


    /**
     * Если до этого там не находилось другого объекта CreditAccount
     * добавляет creditAccount в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public CreditAccount add(CreditAccount creditAccount){
        return (CreditAccount) repository.add(creditAccount);
    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean deleteById(Long id){
        return repository.deleteById(id);
    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public CreditAccount findById(Long id){
        return (CreditAccount) repository.findById(id);
    }

    public List<CreditAccount> findAll(){

        return repository.findAll().stream().map(entity -> (CreditAccount) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public CreditAccount update(CreditAccount creditAccount){
        return (CreditAccount) repository.update(creditAccount);
    }


}
