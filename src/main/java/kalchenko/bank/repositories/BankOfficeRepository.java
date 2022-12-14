package kalchenko.bank.repositories;

import kalchenko.bank.entity.BankOffice;

import java.util.List;

/**
 * Класс-одиночка
 */
public class BankOfficeRepository {

    private static BankOfficeRepository INSTANCE;

    private BankOfficeRepository() {
    }

    public static BankOfficeRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankOfficeRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет bankOffice в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public BankOffice add(BankOffice bankOffice) {
        return (BankOffice) repository.add(bankOffice);
    }

    /**
     * Удаляет офис по id.
     */
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    /**
     * Возвращает офис по id, который хранится в репозитории.
     */
    public BankOffice findById(Long id) {
        return (BankOffice) repository.findById(id);
    }

    /**
     * Возвращает список офисов, которые хранятся в репозитории.
     */
    public List<BankOffice> findAll() {

        return repository.findAll().stream().map(entity -> (BankOffice) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public BankOffice update(BankOffice bankOffice) {
        return (BankOffice) repository.update(bankOffice);
    }

}
