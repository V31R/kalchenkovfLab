package kalchenko.Bank.services.impl;

import kalchenko.Bank.entity.Employee;
import kalchenko.Bank.repositories.EmployeeRepository;
import kalchenko.Bank.services.BankOfficeService;
import kalchenko.Bank.services.EmployeeService;

import java.math.BigDecimal;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository = new EmployeeRepository();
    BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public boolean withdrawMoney(BigDecimal money) {
        return bankOfficeService.withdrawMoney(money);
    }

    @Override
    public boolean depositMoney(BigDecimal money) {
        return bankOfficeService.depositMoney(money);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if(employeeRepository.add(employee)){

            bankOfficeService.addEmployee();
            return employeeRepository.getEmployee();

        }

        return null;
    }

    @Override
    public boolean deleteEmployee() {
        if(bankOfficeService.deleteEmployee()){
            return employeeRepository.delete();
        }
        return false;
    }

    @Override
    public Employee getEmployee() {
        return employeeRepository.getEmployee();
    }
}
