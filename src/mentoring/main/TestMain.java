package mentoring.main;

import mentee.dao.MenteeDAO;
import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;
import mentoring.dto.MentorDTO;
import mentoring.service.ChooseService;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenteeDAO menteeDAO = MenteeDAO.getInstance();
		MentorDAO mentorDAO = MentorDAO.getInstance();
	

		MentorDTO dto =menteeDAO.Mentorinformtion(7);

		
		System.out.println(dto);
		

	}

}

