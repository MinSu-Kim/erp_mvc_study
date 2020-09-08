package erp_mvc_study.dao;

import java.util.List;

import erp_mvc_study.dto.Department;

public interface DepartmentDao {
    List<Department> selectDepartmentByAll();
    
    Department selectDepartmentByNo(Department dept);
    
    int insertDepartment(Department dept);
    int updateDepartment(Department dept);
    int deleteDepartment(Department dept);
    
    int getNextNo();
}
