package erp_mvc_study.service;

import java.util.List;

import erp_mvc_study.dao.DepartmentDao;
import erp_mvc_study.dao.impl.DepartmentDaoImpl;
import erp_mvc_study.dto.Department;

public class DeptService {
    private DepartmentDao dao = DepartmentDaoImpl.getInstance();


    public List<Department> getDepartmentList() {
        return dao.selectDepartmentByAll();
    }

    public int getNextDeptNo() {
        return dao.getNextNo();
    }


    public int addDepartment(Department dept) {
        return dao.insertDepartment(dept);
    }


    public Department getDepartment(Department dept) {
        return dao.selectDepartmentByNo(dept);
    }
    
    public int delDepartment(Department dept) {
        return dao.deleteDepartment(dept);
    }
    
    public int modifyDepartment(Department dept) {
        return dao.updateDepartment(dept);
    }
}
