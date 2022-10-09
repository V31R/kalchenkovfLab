package kalchenko.Bank.services;

import kalchenko.Bank.entity.Employee;

public interface EmployeeService extends BankOperations {

    public Employee addEmployee(Employee employee);
    public boolean deleteEmployee();

    public Employee getEmployee();

}
