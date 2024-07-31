package mentee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mentoring.dto.MenteeDTO;
import mentoring.dto.MentorDTO;


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

	public int Login(String id, String password) {
		int mentee_seq =-1;
		
		getCnnection();
		String sql="select * from  mentee where id = ? and pwd=  ?";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs =  pstmt.executeQuery();
			
			if(rs.next())mentee_seq=rs.getInt("mentee_seq");
			
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
		String sql="insert into   mentee values(mentee_seq.NEXTVAL,?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			
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
	
	
	public String  MentoringCheck(int mentee_seq) {
		String name=null;
		getCnnection();
		
		String sql="select MENTOR_SEQ from MENTORING where  MENTEE_SEQ =?";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, mentee_seq);
			
			rs =  pstmt.executeQuery();
			
			if(rs.next()) {
				int mentor_seq=rs.getInt("mentor_seq");
          
				String sql1="select * from mentor where mentor_seq = ?";
				pstmt=con.prepareStatement(sql1);
				pstmt.setInt(1,mentor_seq);
				rs =  pstmt.executeQuery();
				
				if(rs.next())name=rs.getString("name");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				if(rs!=null)rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	
	public MentorDTO Mentorinformtion(int mentee_seq) {
		//대기목록->멘토 스퀸스 뽑기->멘토 테이블 뽑기
		 MentorDTO dto =null;
		 getCnnection();
		 //멘토 스퀸시 뽑기
		 String sql="select MENTOR_SEQ from MENTORING where  MENTEE_SEQ =?";
		 try {
			 pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, mentee_seq);
				
				rs =  pstmt.executeQuery();
				
				
				if(rs.next()) {
					int mentor_seq=rs.getInt("mentor_seq");
					//뽑은 스퀸시로 멘토 정보 뽑기
					String sql1="select * from mentor where mentor_seq = ?";
					pstmt=con.prepareStatement(sql1);
					pstmt.setInt(1,mentor_seq);
					rs =  pstmt.executeQuery();
					
					if(rs.next()) {
						dto=new  MentorDTO();
						dto.setName(rs.getString("name"));
						dto.setDepartment("department");
						dto.setEmail(rs.getString("email"));
						dto.setPhone(rs.getString("phone"));
					}
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				if(rs!=null)rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		return dto;
		
	}
	
	public List<MentorDTO> DepartmenSort(int department){
		List<MentorDTO> list = new ArrayList<>();
		String s="";
		String sql=null;
		 getCnnection();
		
		 
		 try {
		        // 데이터베이스 연결 설정

		        if (department < 5) {
		            if (department == 1) s = "프론트엔드";
		            else if (department == 2) s = "백엔드";
		            else if (department == 3) s = "네트워크";
		            else if (department == 4) s = "클라우드";
		            
		            // 문자열에서 공백 제거
		            s = s.trim();
		            sql = "select * from MENTOR where department = ? order by mentor_seq asc";
		        } else if (department == 5) {
		            sql = "select * from MENTOR order by mentor_seq asc";
		        }

		        // PreparedStatement 또는 Statement 생성
		        if (sql != null) {
		            if (department < 5) {
		                pstmt = con.prepareStatement(sql);
		                pstmt.setString(1, s);
		            } else {
		                pstmt = con.prepareStatement(sql);
		            }

		            // 쿼리 실행
		            rs = pstmt.executeQuery();

		            // 결과 처리
		            while (rs.next()) {
		                MentorDTO dto = new MentorDTO(); // assuming mentor_seq is of type Integer

		                dto.setMentor_seq(rs.getInt("mentor_seq"));
		                dto.setName(rs.getString("name"));
		                dto.setDepartment(rs.getString("department")); // assuming department column exists in the result
		                list.add(dto);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmt != null) pstmt.close();
		            if (con != null) con.close();
		            if (rs != null) rs.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return list;
		}
	
	public int MentoringRequest(int mentee_seq, int mentor_seq){
		int check=0;
		 getCnnection();
<<<<<<< HEAD
		 String sql="insert into MENTORING  VALUES(?,?)";
=======
		 String sql="insert into waiting  VALUES(?,?)";
>>>>>>> b68475cce725f43571173f1517232a9afdcc1f7c
		 
		 try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,mentor_seq);
			pstmt.setInt(2,mentee_seq);
			
			check=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return check;
	}
	
	
	
	public int RequestCancell(int mentee_seq, int mentor_seq) {
		int cancell =0;
		getCnnection();
		 String sql="Delete  waiting where mentor_seq=? and mentee_seq =?";
		
		 try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,mentor_seq);
			pstmt.setInt(2,mentee_seq);
			
			cancell=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return cancell;
	}
	
	public  List<MentorDTO> RequestList(int mentee_seq){
		List<MentorDTO> list = new ArrayList<>();
		getCnnection();
		 String sql="select * from   MENTORING where mentee_seq = ? order by mentor_seq asc";
		 try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,mentee_seq);
			rs =  pstmt.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
