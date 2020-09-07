package erp_mvc_study.ds;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HikariJNDI {
    private static DataSource ds;
    
    private HikariJNDI() {}
    
    static {
        try {
            InitialContext ic= new InitialContext();                              //1. JNDI 서버 객체 생성
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/localhikari");   //2. lookup()
            System.out.println("ds : " + ds);
        } catch (NamingException e) {
            e.printStackTrace();
        } 
    }
    
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
