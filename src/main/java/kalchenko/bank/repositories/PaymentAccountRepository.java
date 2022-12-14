package kalchenko.bank.repositories;

import kalchenko.bank.entity.PaymentAccount;

import java.util.List;

/**
 * Класс-одиночка
 */
public class PaymentAccountRepository {

    private static PaymentAccountRepository INSTANCE;

    private PaymentAccountRepository() {
    }

    public static PaymentAccountRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PaymentAccountRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет paymentAccount в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public PaymentAccount add(PaymentAccount paymentAccount) {
        return (PaymentAccount) repository.add(paymentAccount);
    }

    /**
     * Удаляет платёжный счёт по id.
     */
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    /**
     * Возвращает счёт, который хранится в репозитории.
     */
    public PaymentAccount findById(Long id) {
        return (PaymentAccount) repository.findById(id);
    }

    /**
     * Возвращает список счетов, которые хранятся в репозитории.
     */
    public List<PaymentAccount> findAll() {

        return repository.findAll().stream().map(entity -> (PaymentAccount) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public PaymentAccount update(PaymentAccount paymentAccount) {
        return (PaymentAccount) repository.update(paymentAccount);
    }

}
