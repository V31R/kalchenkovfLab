package kalchenko.bank.repositories;

import kalchenko.bank.entity.Employee;

import java.util.List;

public class EmployeeRepository {

    private static EmployeeRepository INSTANCE;

    private EmployeeRepository(){}

    public static EmployeeRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EmployeeRepository();
        }

        return INSTANCE;
    }

    private final EntityRepository repository = new EntityRepository();

    /**
     * Если до этого там не находилось другого объекта Employee
     * добавляет employee в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public Employee add(Employee employee){
       return (Employee) repository.add(employee);
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
    public Employee findById(Long id){
        return (Employee) repository.findById(id);
    }

    /**
     * Возвращает список клиентов, которые хранятся в репозитории.
     */
    public List<Employee> findAll(){

        return repository.findAll().stream().map(entity -> (Employee) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public Employee update(Employee employee){
        return (Employee) repository.update(employee);
    }

}
