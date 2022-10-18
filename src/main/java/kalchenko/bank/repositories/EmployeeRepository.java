package kalchenko.bank.repositories;

import kalchenko.bank.entity.Employee;

public class EmployeeRepository {

    private Employee employee = null;

    public EmployeeRepository(){}

    /**
     * Если до этого там не находилось другого объекта Employee
     * добавляет employee в репозиторий и возвращает добавленный объект,
     * иначе возвращает null.
     */
    public boolean add(Employee employee){
        var isEmpty = this.employee == null;

        if (isEmpty && employee != null){

            this.employee = new Employee(employee);

        }

        return isEmpty;
    }

    /**
     * Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean delete(){
        if(this.employee == null){
            return false;
        }

        this.employee = null;
        return true;
    }

    /**
     * Возвращает объект, который хранится в репозитории.
     */
    public Employee getEmployee(){
        if(this.employee == null){

            return null;

        }

        return this.employee;
    }

    /**
     * Если объект существует, то обновляет его и возвращает истину,
     * иначе возвращает ложь.
     */
    public boolean update(Employee employee){
        if(this.employee == null && employee != null){

            return false;

        }

        this.employee = employee;
        return true;
    }

}
