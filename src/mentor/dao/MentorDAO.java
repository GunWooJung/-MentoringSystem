package mentor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mentoring.dto.MenteeDTO;
import mentoring.dto.MentorDTO;

public class MentorDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "C##JAVA";
	private String password = "1234";

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MentorDAO instance = new MentorDAO();

	public static MentorDAO getInstance() {
		return instance;
	}

	public void getConnection() {
		try {
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int Login(String id, String pwd) {
		int mento_seq = 0;
		getConnection();
		String sql = "select * from mentor where id = ? and pwd = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				mento_seq = rs.getInt("mentor_seq");
			}else
				mento_seq = -1;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return mento_seq;
	}

	public boolean IdCheck(String id) {
		boolean exist = false;
		getConnection();

		String sql = "select id from mentor where id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				exist = true;
			}else
				exist = false;	

		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}

	public int Join(MentorDTO dto) {
		int su = 0;
		getConnection();
		String sql = "insert into mentor values(mentor_seq.NEXTVAL,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getDepartment());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setInt(7, dto.getStatus());
			su = pstmt.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}

	public String MentoringCheck(int mentor_seq) {
		String mentee_name = null;
		getConnection();
		String sql = "select mentee_seq from mentoring where mentor_seq = ?";
		String sql2 = "select name from mentee where mentee_seq = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mentor_seq);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int mentee_seq = rs.getInt("mentee_seq");
	            pstmt = con.prepareStatement(sql2);
	            pstmt.setInt(1, mentee_seq);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	            	mentee_name = rs.getString("name");
	            }
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return mentee_name;
	}
	
	public MenteeDTO MenteeInformation(int mentor_seq){
		MenteeDTO menteeDTO = null;	
		getConnection();
		String sql = "select * from waiting where mentor_seq = ?";
		String sql2 = "select * from mentee where mentee_seq = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mentor_seq);
			rs = pstmt.executeQuery();

			if(rs.next()) {		
				int mentee_seq = rs.getInt("mentee_seq");
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, mentee_seq);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					menteeDTO = new MenteeDTO();
					menteeDTO.setName(rs.getString("name"));
					menteeDTO.setEmail(rs.getString("email"));
					menteeDTO.setPhone(rs.getString("phone"));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return menteeDTO;
	}
}
