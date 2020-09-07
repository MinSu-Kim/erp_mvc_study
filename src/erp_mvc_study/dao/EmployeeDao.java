package erp_mvc_study.dao;

import erp_mvc_study.dto.Employee;

public interface EmployeeDao {
    int confirmEmail(Employee emp);
    
    int insertEmployee(Employee emp);
    
    int loginEmployee(Employee emp);
    
    Employee selectEmployeeByEmail(Employee emp);
    
    int updateEmployee(Employee emp);
    
    int deleteEmployee(Employee emp);
}
