package kalchenko.bank.repositories;

import kalchenko.bank.entity.Bank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankRepository {

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет bank в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public Bank add(Bank bank) {

        return (Bank) repository.add(bank);

    }

    /**
     * Удаляет банк по id.
     */
    public boolean deleteById(Long id) {

        return repository.deleteById(id);

    }

    /**
     * Возвращает банк по id, который хранится в репозитории.
     */
    public Bank findById(Long id) {

        return (Bank) repository.findById(id);

    }

    /**
     * Возвращает список банков, которые хранятся в репозитории.
     */
    public List<Bank> findAll() {

        return repository.findAll().stream().map(entity -> (Bank) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает его,
     * иначе возвращает null.
     */
    public Bank update(Bank bank) {

        return (Bank) repository.update(bank);

    }

}
