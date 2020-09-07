package erp_mvc_study.service;

import erp_mvc_study.dao.EmployeeDao;
import erp_mvc_study.dao.impl.EmployeeDaoImpl;
import erp_mvc_study.dto.Employee;

public class JoinService {
    private EmployeeDao dao = EmployeeDaoImpl.getInstance();

    public int checkConfirmByEmail(Employee emp) {
        return dao.confirmEmail(emp);
    }
    
    public int joinEmployee(Employee emp) {
        return dao.insertEmployee(emp);
    }
    
    public int loginEmployee(Employee emp) {
        return dao.loginEmployee(emp);
    }

    public Employee selectEmployeeByEmail(Employee emp) {
        return dao.selectEmployeeByEmail(emp);
    }
    
    public int modifyEmployee(Employee emp) {
        return dao.updateEmployee(emp);
    }
    
    public int removeEmployee(Employee emp) {
        return dao.deleteEmployee(emp);
    }
}
