package kalchenko.bank.repositories;

import kalchenko.bank.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRepository {

    private final EntityRepository repository = new EntityRepository();

    /**
     * Добавляет employee в репозиторий и возвращает добавленный объект,
     * если bank не был равен null, иначе возвращает null.
     */
    public Employee add(Employee employee) {
        return (Employee) repository.add(employee);
    }

    /**
     * Удаляет работника по id.
     */
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    /**
     * Возвращает работника по id, который хранится в репозитории.
     */
    public Employee findById(Long id) {
        return (Employee) repository.findById(id);
    }

    /**
     * Возвращает список работников, которые хранятся в репозитории.
     */
    public List<Employee> findAll() {

        return repository.findAll().stream().map(entity -> (Employee) entity).toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public Employee update(Employee employee) {
        return (Employee) repository.update(employee);
    }

}
