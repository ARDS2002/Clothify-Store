package service.custom;

import dto.Employee;

import java.util.List;

public interface EmployeeService {

    boolean addEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Employee employee);

    Employee searchEmployee(Long id);

    List<Employee> getEmployee();


}
