package erp_mvc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import erp_mvc_study.dao.EmployeeDao;
import erp_mvc_study.ds.HikariJNDI;
import erp_mvc_study.dto.Department;
import erp_mvc_study.dto.Employee;
import erp_mvc_study.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
    
    private EmployeeDaoImpl() {
        // TODO Auto-generated constructor stub
    }

    public static EmployeeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public int confirmEmail(Employee emp) {
        String sql = "SELECT 1 FROM EMPLOYEE WHERE EMAIL = ?";
        try (Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setString(1, emp.getEmail());
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                     return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
    }

    @Override
    public int insertEmployee(Employee emp) {
        String sql = "INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, PASSWD, TEL) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, emp.getEmpNo());
            pstmt.setString(2, emp.getEmpName());
            pstmt.setInt(3, emp.getTitle().getTitleNo()); //tno 추후 수정
            pstmt.setInt(4, emp.getManager().getEmpNo());//manager 추후 수정
            pstmt.setInt(5, emp.getSalary());//salary 추후 수정
            pstmt.setInt(6, emp.getDept().getDeptNo());//dno 추후 수정
            pstmt.setString(7, emp.getEmail());
            pstmt.setString(8, emp.getPasswd());
            pstmt.setString(9, emp.getTel());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int loginEmployee(Employee emp) {
        String sql = "SELECT 1 FROM EMPLOYEE WHERE EMAIL = ? AND PASSWD = ?";
        try(Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, emp.getEmail());
            pstmt.setString(2, emp.getPasswd());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }


    @Override
    public Employee selectEmployeeByEmail(Employee emp) {
        String sql = " SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, REGDATE, TEL, PASSWD" 
                   + "   FROM EMPLOYEE"
                   + "  WHERE EMAIL = ? AND PASSWD = ?";
        try (Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setString(1, emp.getEmail());
            pstmt.setString(2, emp.getPasswd());
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                     return getEmplyee(rs);
                }
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int updateEmployee(Employee emp) {
        String sql =   "UPDATE EMPLOYEE"
                     + "   SET TNO=?, MANAGER=?, SALARY=?, DNO=?, PASSWD=?, TEL=?"
                     + " WHERE EMAIL=?";
        try(Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, emp.getTitle().getTitleNo());
            pstmt.setInt(2, emp.getManager().getEmpNo());
            pstmt.setInt(3, emp.getSalary());
            pstmt.setInt(4, emp.getDept().getDeptNo());
            pstmt.setString(5, emp.getPasswd());
            pstmt.setString(6, emp.getTel());
            pstmt.setString(7, emp.getEmail());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int deleteEmployee(Employee emp) {
        String sql = "DELETE FROM EMPLOYEE WHERE EMAIL = ?";
        try(Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, emp.getEmail());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }
    
    private Employee getEmplyee(ResultSet rs) throws SQLException {
        int empNo = rs.getInt("EMP_NO");
        String empName= rs.getString("EMP_NAME");
        Title title = new Title(rs.getInt("TNO"));
        Employee manager = new Employee(rs.getInt("MANAGER"));
        int salary = rs.getInt("SALARY");
        Department dept = new Department(rs.getInt("DNO"));
        String email = rs.getString("EMAIL");
        Date regDate = rs.getTimestamp("REGDATE");
        String tel = rs.getString("TEL");
        String picUrl = rs.getString("PIC_URL");
        try {
            String titleName = rs.getString("TITLE_NAME");
            title.setTitleName(titleName);
            
            String deptName = rs.getString("DEPT_NAME");
            dept.setDeptName(deptName);
            
            String managerName = rs.getString("MANAGER_NAME");
            manager.setEmpName(managerName);
        }catch(SQLException e) {
            
        }
        return new Employee(empNo, empName, title, manager, salary, dept, email, regDate, tel, picUrl);
    }
    

    @Override
    public List<Employee> selectEmployeeByAll() {
        String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, TEL, EMAIL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME "
                    +  "FROM VW_EMPLOYEE_JOIN";
        try(Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            if (rs.next()) {
                List<Employee> list = new ArrayList<>();
                do {
                    list.add(getEmplyee(rs));
                }while(rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

}
