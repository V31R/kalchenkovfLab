package kalchenko.bank.repositories;

import kalchenko.bank.entity.PaymentAccount;

import java.util.List;

public class PaymentAccountRepository {

    private static PaymentAccountRepository INSTANCE;

    private PaymentAccountRepository(){}

    public static PaymentAccountRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaymentAccountRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Если до этого там не находилось другого объекта PaymentAccount
     * добавляет paymentAccount в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public PaymentAccount add(PaymentAccount paymentAccount){
       return (PaymentAccount) repository.add(paymentAccount);
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
    public PaymentAccount findById(Long id){
       return (PaymentAccount) repository.findById(id);
    }

    /**
     * Возвращает список аккаунтов, которые хранятся в репозитории.
     */
    public List<PaymentAccount> findAll(){

        return repository.findAll().stream().map(entity -> (PaymentAccount) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public PaymentAccount update(PaymentAccount paymentAccount){
        return (PaymentAccount) repository.update(paymentAccount);
    }

}
