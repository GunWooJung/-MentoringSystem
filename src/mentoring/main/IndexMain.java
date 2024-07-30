package mentoring.main;

import mentee.dao.MenteeDAO;
import mentoring.dto.MenteeDTO;
import mentoring.service.ChooseService;

public class IndexMain {

	public static void main(String[] args) {
		MenteeDAO menteeDAO = MenteeDAO.getInstance();
		MenteeDTO menteeDTO =new MenteeDTO(); 
		int mentee_seq=menteeDAO.Logun("아이디 123", "123");
		boolean idchek=menteeDAO.IdCheck("아이디 123");
		int line=menteeDAO.Join(menteeDTO);
		
		
		ChooseService service = new ChooseService();
		//service.menu();
		System.out.println("프로그램을 종료합니다.");
		
		
		
		System.out.println(mentee_seq);
		System.out.println(idchek);
		System.out.println(line);
	
	}
}
