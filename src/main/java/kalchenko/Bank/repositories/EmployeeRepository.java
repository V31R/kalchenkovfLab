package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.Employee;

public class EmployeeRepository {

    Employee employee = null;

    public EmployeeRepository(){}

    public boolean add(Employee employee){
        var isEmpty = this.employee == null;

        if (isEmpty){

            this.employee = new Employee(employee);

        }

        return isEmpty;
    }


    public boolean delete(){
        if(this.employee == null){

            return false;

        }

        this.employee = null;
        return true;
    }

    public Employee getEmployee(){
        if(this.employee == null){

            return null;

        }

        return this.employee;
    }

    public boolean update(Employee employee){
        if(this.employee == null){

            return false;

        }

        this.employee = employee;
        return true;
    }

}
