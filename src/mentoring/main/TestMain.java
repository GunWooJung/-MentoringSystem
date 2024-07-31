package mentoring.main;

import mentee.dao.MenteeDAO;
import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;
import mentoring.service.ChooseService;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenteeDAO menteeDAO = MenteeDAO.getInstance();
		MentorDAO mentorDAO = MentorDAO.getInstance();
	
		String  name =menteeDAO.MentoringCheck(7);
		//System.out.println(name);
		
		MenteeDTO menteeDTO = mentorDAO.MenteeInformation(1);
        System.out.println(menteeDTO.getName());
	}

}

