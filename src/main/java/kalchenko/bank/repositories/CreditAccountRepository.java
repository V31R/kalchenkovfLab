package kalchenko.bank.repositories;

import kalchenko.bank.entity.CreditAccount;

import java.util.List;

/**
 * Класс-одиночка
 */
public class CreditAccountRepository {

    private static CreditAccountRepository INSTANCE;

    private CreditAccountRepository() {
    }

    public static CreditAccountRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreditAccountRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();


    /**
     * Добавляет creditAccount в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public CreditAccount add(CreditAccount creditAccount) {
        return (CreditAccount) repository.add(creditAccount);
    }

    /**
     * Удаляет кредитный счёт по id.
     */
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    /**
     * Возвращает кредитный счёт по id, который хранится в репозитории.
     */
    public CreditAccount findById(Long id) {
        return (CreditAccount) repository.findById(id);
    }

    /**
     * Возвращает список кредитныч счётов, которые хранятся в репозитории.
     */
    public List<CreditAccount> findAll() {

        return repository.findAll().stream().map(entity -> (CreditAccount) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public CreditAccount update(CreditAccount creditAccount) {
        return (CreditAccount) repository.update(creditAccount);
    }


}
