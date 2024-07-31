package mentoring.main;

import java.util.ArrayList;

import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;

public class TestMain2 {
	public static void main(String[] args) {
		MentorDAO mentorDAO = MentorDAO.getInstance();
	
		//MenteeDTO menteeDTO = mentorDAO.MenteeInformation(1);
		//boolean end = mentorDAO.MentoringEnd(1);
		
		//boolean accept = mentorDAO.MentoringAccept(1, 1);
		
		ArrayList<MenteeDTO> menteeDTO = mentorDAO.MenteeList(1);
		for(MenteeDTO dto : menteeDTO) {
			System.out.println(dto.getName());
		}
	}
}
