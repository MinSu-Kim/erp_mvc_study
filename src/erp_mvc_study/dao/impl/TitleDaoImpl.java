package erp_mvc_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_mvc_study.dao.TitleDao;
import erp_mvc_study.ds.HikariJNDI;
import erp_mvc_study.dto.Title;

public class TitleDaoImpl implements TitleDao {
	private static final TitleDaoImpl instance = new TitleDaoImpl();
	
	private TitleDaoImpl() {}

	public static TitleDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public Title selectTitleByNo(Title title) {
	    String sql = "select title_no, title_name from title where title_no=?";
		try (Connection con = HikariJNDI.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					 return getTitle(rs);
				}
			}
		} catch (SQLException e) {
		    throw new CustomSQLException(e);
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("title_no");
		String titleName = rs.getString("title_name");
		return new Title(titleNo, titleName);
	}

	@Override
	public List<Title> selectTitleByAll() {
	    String sql = "select title_no, title_name from title";
		try (Connection con = HikariJNDI.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
		    throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
	    String sql = "insert into title values(?, ?)";
		try(Connection con = HikariJNDI.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
		    throw new CustomSQLException(e);
		}
	}

	@Override
	public int updateTitle(Title title) {
	    String sql = "update title set title_name=? where title_no = ?";
		try(Connection con = HikariJNDI.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.getTitleName());
			pstmt.setInt(2, title.getTitleNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
		    throw new CustomSQLException(e);
		}
	}

	@Override
	public int deleteTitle(Title title) {
	    String sql = "delete from title where title_no = ?";
		try(Connection con = HikariJNDI.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.getTitleNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
		    throw new CustomSQLException(e);
		}
	}

    @Override
    public int getNextNo() {
        String sql = "SELECT MAX(TITLE_NO)+1 FROM TITLE";
        try (Connection con = HikariJNDI.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
    }

}
