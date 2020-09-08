package erp_mvc_study.service;

import java.util.List;

import erp_mvc_study.dao.EmployeeDao;
import erp_mvc_study.dao.impl.EmployeeDaoImpl;
import erp_mvc_study.dto.Employee;

public class EmpService {
    private EmployeeDao dao = EmployeeDaoImpl.getInstance();

    public List<Employee> getEmpList(){
        return dao.selectEmployeeByAll();
    }
}
