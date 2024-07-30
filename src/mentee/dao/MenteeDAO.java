package mentee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mentoring.dto.MenteeDTO;


public class MenteeDAO {
	
	
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost :1521:xe";
	private String username="c##java";
	private String password ="1234";
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//private static MemberDAO instance;
	private static MenteeDAO instance=new MenteeDAO();
	
	public MenteeDAO() {
		try {
			Class.forName(driver);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//생성
				
	}
	
	public static MenteeDAO getInstance() {
		/*if(instance ==null) {
			synchronized(MemberDAO.class) {
				instance=new MemberDAO(); 
			}
		}*/
		return instance;
	}
	
	
	
	
public void getCnnection() {
		
		try {
		con = DriverManager.getConnection(url,username,password);
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int Logun(String id, String password) {
		int mentee_seq =-1;
		
		getCnnection();
		String sql="select * from  mentee where id = ? and pwd=  ?";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs =  pstmt.executeQuery();
			
			if(rs.next())mentee_seq=rs.getInt(mentee_seq);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return mentee_seq;
		
	}
	
	
	public boolean IdCheck(String id) {
		boolean idchek=false;
		
		getCnnection();
		String sql="select * from  mentee where id = ?";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs =  pstmt.executeQuery();
			
			if(rs.next())idchek=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return idchek;
		
	}
	
	public int Join(MenteeDTO dto) {
		int line=0;
		getCnnection();
		String sql="insert into   mentee values(mentee_seq.NEXTVAL,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			
			line=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return line;
		
	}

}
