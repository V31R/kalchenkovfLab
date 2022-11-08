package kalchenko.bank.repositories;

import kalchenko.bank.entity.BankAtm;

import java.util.List;

/**
 * Класс-одиночка
 */
public class BankAtmRepository {

    private static BankAtmRepository INSTANCE;

    private BankAtmRepository() {
    }

    public static BankAtmRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankAtmRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет bankAtm в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public BankAtm add(BankAtm bankAtm) {
        return (BankAtm) repository.add(bankAtm);
    }

    /**
     * Удаляет банкомат по id.
     */
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    /**
     * Возвращает банкомат по id, который хранится в репозитории.
     */
    public BankAtm findById(Long id) {
        return (BankAtm) repository.findById(id);
    }

    /**
     * Возвращает список банкоматов, которые хранятся в репозитории.
     */
    public List<BankAtm> findAll() {

        return repository.findAll().stream().map(entity -> (BankAtm) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public BankAtm update(BankAtm bankAtm) {
        return (BankAtm) repository.update(bankAtm);
    }

}
