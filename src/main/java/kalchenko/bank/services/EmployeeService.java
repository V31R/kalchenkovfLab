package kalchenko.bank.services;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.NotExistedObjectException;

import java.util.List;

public interface EmployeeService extends BankOperations {

    /**
     * Добавляет employee в репозиторий, извещает об этом связанный bankOffice.
     */
    Employee addEmployee(Employee employee) throws NotExistedObjectException, IdException;

    /**
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    boolean deleteEmployeeById(Long id) throws NotExistedObjectException, IdException;

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    Employee getEmployeeById(Long id) throws IdException;

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
