package kalchenko.bank.services;

import kalchenko.bank.entity.Employee;

import java.util.List;

public interface EmployeeService extends BankOperations {

    /**
     * Добавляет employee в репозиторий, если добавление было успешно
     * извещает об этом связанный bankOffice.
     */
    Employee addEmployee(Employee employee);

    /**
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    boolean deleteEmployeeById(Long id);

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    Employee getEmployeeById(Long id);

    /**
     * Возвращает все банки, которые хранятся в репозитории.
     */
    List<Employee> getAllEmployees();


}
