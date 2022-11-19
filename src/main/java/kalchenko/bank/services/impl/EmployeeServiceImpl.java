package kalchenko.bank.services.impl;

import kalchenko.bank.entity.BankOffice;
import kalchenko.bank.entity.Employee;
import kalchenko.bank.exceptions.IdException;
import kalchenko.bank.exceptions.NegativeSumException;
import kalchenko.bank.exceptions.NotExistedObjectException;
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
        final BigDecimal salary = BigDecimal.ONE;
        final boolean inOffice = true;
        final boolean canApplyLoan = true;
        final int years = 20;

        return new Employee(String.format("Employee_name_%d", number++), LocalDate.now().minusYears(years), "job",
                inOffice, bankOffice, canApplyLoan, salary);
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal money) throws NegativeSumException, IdException {
        var employee = employeeRepository.findById(id);
        if(employee==null){
            throw new IdException();
        }
        return bankOfficeService.withdrawMoney(employee.getBankOffice().getId(), money);
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal money) throws IdException, NegativeSumException {
        var employee = employeeRepository.findById(id);
        if(employee==null){
            throw new IdException();
        }
        return bankOfficeService.depositMoney(employee.getBankOffice().getId(), money);
    }

    @Override
    public Employee addEmployee(Employee employee) throws NotExistedObjectException, IdException {

        var newEmployee = employeeRepository.add(employee);
        var office = newEmployee.getBankOffice();

        if (office != null) {
            bankOfficeService.addEmployee(office.getId());
        }

        return newEmployee;

    }

    @Override
    public boolean deleteEmployeeById(Long id) throws NotExistedObjectException, IdException {

        var officeId = employeeRepository.findById(id).getBankOffice().getId();

        if (bankOfficeService.deleteEmployee(officeId)) {
            return employeeRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public Employee getEmployeeById(Long id) throws IdException {
        var employee = employeeRepository.findById(id);
        if(employee == null){
            throw new IdException();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesByOffice(Long officeId) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getBankOffice().getId().compareTo(officeId) == 0)
                .toList();
    }
}
