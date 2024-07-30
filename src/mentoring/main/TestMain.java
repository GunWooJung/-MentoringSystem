package mentoring.main;

import mentee.dao.MenteeDAO;
import mentoring.dto.MenteeDTO;
import mentoring.service.ChooseService;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenteeDAO menteeDAO = MenteeDAO.getInstance();
		MenteeDTO menteeDTO =new MenteeDTO(); 
		menteeDTO.setId("아이디 123");
		menteeDTO.setPwd("123");		
		int line=menteeDAO.Join(menteeDTO);
		int mentee_seq=menteeDAO.Login("아이디 123", "123");
		boolean idchek=menteeDAO.IdCheck("아이디 123");

		
		
		System.out.println(mentee_seq);
		System.out.println(idchek);
		System.out.println(line);
	}

}
