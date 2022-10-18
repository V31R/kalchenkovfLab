package kalchenko.bank.services;

import kalchenko.bank.entity.Employee;

public interface EmployeeService extends BankOperations {

    public Employee addEmployee(Employee employee);
    public boolean deleteEmployee();

    public Employee getEmployee();

}
