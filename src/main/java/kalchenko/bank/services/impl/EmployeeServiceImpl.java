package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Employee;
import kalchenko.bank.repositories.EmployeeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.EmployeeService;

import java.math.BigDecimal;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private BankOfficeService bankOfficeService;

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
