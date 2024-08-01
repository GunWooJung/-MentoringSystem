package mentoring.main;

import java.util.List;

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
		
		
		MentorDTO list =menteeDAO.Mentorinformtion(10);

		
		System.out.println(list);
		

	}

}

