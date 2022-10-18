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

    /*
     * Списывает деньги из связанного объекта bankOffice.
     */
    @Override
    public boolean withdrawMoney(BigDecimal money) {
        return bankOfficeService.withdrawMoney(money);
    }

    /*
     * Вносит деньги в связанный объекта bankOffice.
     */
    @Override
    public boolean depositMoney(BigDecimal money) {
        return bankOfficeService.depositMoney(money);
    }

    /*
     * Добавляет employee в репозиторий, если добавление было успешно
     * извещает об этом связанный bankOffice.
     */
    @Override
    public Employee addEmployee(Employee employee) {
        if(employeeRepository.add(employee)){

            bankOfficeService.addEmployee();
            return employeeRepository.getEmployee();

        }

        return null;
    }

    /*
     * Удаляет объект, извещает об этом связанный BankOffice.
     */
    @Override
    public boolean deleteEmployee() {
        if(bankOfficeService.deleteEmployee()){
            return employeeRepository.delete();
        }
        return false;
    }

    /*
     * Возвращает объект, который хранится в репозитории.
     */
    @Override
    public Employee getEmployee() {
        return employeeRepository.getEmployee();
    }
}
