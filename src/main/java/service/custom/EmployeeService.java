package service.custom;

import dto.Employee;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {

    boolean addEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Employee employee);

    Employee searchEmployee(Long id);

    List<Employee> getEmployee();


}
