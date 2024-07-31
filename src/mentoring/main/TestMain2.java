package mentoring.main;

import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;

public class TestMain2 {
	public static void main(String[] args) {
		MentorDAO mentorDAO = MentorDAO.getInstance();
	
		MenteeDTO menteeDTO = mentorDAO.MenteeInformation(1);
		boolean end = mentorDAO.MentoringEnd(1);
		
		boolean accept = mentorDAO.MentoringAccept(1, 1);
		System.out.println(accept);
	}
}
