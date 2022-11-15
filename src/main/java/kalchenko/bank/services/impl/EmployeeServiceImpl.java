package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;
import kalchenko.bank.repositories.EmployeeRepository;
import kalchenko.bank.services.BankOfficeService;
import kalchenko.bank.services.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс-одиночка
 */
public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeServiceImpl INSTANCE;

    private EmployeeServiceImpl() {
    }

    public static EmployeeServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeServiceImpl();
        }

        return INSTANCE;
    }

    private final EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
    private final BankOfficeService bankOfficeService = BankOfficeServiceImpl.getInstance();

    private static int number = 0;
    public Employee createEmployee(BankOffice bankOffice) {
        BigDecimal salary = BigDecimal.ONE;
        boolean inOffice = true;
        boolean canApplyLoan = true;

        return new Employee(String.format("Employee_name_%d", number++), LocalDate.now().minusYears(20), "job",
                inOffice, bankOffice, canApplyLoan, salary);
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) {
        var employee = employeeRepository.findById(id);
        return bankOfficeService.withdrawMoney(employee.getBankOffice().getId(), money);
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) {
        var employee = employeeRepository.findById(id);
        return bankOfficeService.depositMoney(employee.getBankOffice().getId(), money);
    }

    @Override
    public Employee addEmployee(Employee employee) {

        var newEmployee = employeeRepository.add(employee);
        var office = newEmployee.getBankOffice();

        if (office != null) {
            bankOfficeService.addEmployee(office.getId());
        }

        return newEmployee;

    }

    @Override
    public boolean deleteEmployeeById(Long id) {

        var officeId = employeeRepository.findById(id).getBankOffice().getId();

        if (bankOfficeService.deleteEmployee(officeId)) {
            return employeeRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
