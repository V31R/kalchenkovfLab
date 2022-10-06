package kalchenko.Bank.repositories;

import kalchenko.Bank.entity.Employee;

public class EmployeeRepository {

    Employee employee = null;

    EmployeeRepository(){}

    public boolean add(Employee employee){
        var isEmpty = this.employee == null;

        if (isEmpty){

            this.employee = new Employee(employee);

        }

        return isEmpty;
    }


    public boolean deleteById(Long id){
        if(this.employee == null || !this.employee.getId().equals(id)){

            return false;

        }

        this.employee = null;
        return true;
    }

    public Employee findById(Long id){
        if(this.employee == null || !this.employee.getId().equals(id)){

            return null;

        }

        return this.employee;
    }

    public boolean update(Employee employee){
        if(this.employee == null || !this.employee.getId().equals(employee.getId())){

            return false;

        }

        this.employee = employee;
        return true;
    }

}
