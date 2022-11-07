package kalchenko.bank.services.impl;

import kalchenko.bank.entity.Employee;
import kalchenko.bank.repositories.EmployeeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.EmployeeService;

import java.math.BigDecimal;

public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeServiceImpl INSTANCE;

    private EmployeeServiceImpl(){}

    public static EmployeeServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EmployeeServiceImpl();
        }

        return INSTANCE;
    }

    private EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
    private BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();

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
