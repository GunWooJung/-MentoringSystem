package mentoring.main;

import mentee.dao.MenteeDAO;
import mentoring.dto.MenteeDTO;
import mentoring.service.ChooseService;

public class IndexMain {

	public static void main(String[] args) {

		ChooseService service = new ChooseService();
		service.menu();
		System.out.println("프로그램을 종료합니다.");
		
		

	}
}

