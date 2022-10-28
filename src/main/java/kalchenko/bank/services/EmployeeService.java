package kalchenko.bank.services;

import kalchenko.bank.entity.Employee;

public interface EmployeeService extends BankOperations {

    /**
     * Добавляет employee в репозиторий, если добавление было успешно
     * извещает об этом связанный bankOffice.
     */
    Employee addEmployee(Employee employee);

    /**
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    boolean deleteEmployee();

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    Employee getEmployee();

}
