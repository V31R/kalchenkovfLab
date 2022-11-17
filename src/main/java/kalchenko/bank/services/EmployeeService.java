package kalchenko.bank.services;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;

import java.util.List;

public interface EmployeeService extends BankOperations {

    /**
     * Добавляет employee в репозиторий, извещает об этом связанный bankOffice.
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
     * Возвращает все работников, которые хранятся в репозитории.
     */
    List<Employee> getAllEmployees();

    /**
     * Возвращает все работников, которые хранятся в репозитории, принадлежащие офису officeId.
     */
    List<Employee>getAllEmployeesByOffice(Long officeId);

    /**
     * Создаёт работника
     */
    Employee createEmployee(BankOffice bankOffice);

}
