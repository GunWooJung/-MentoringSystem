package mentoring.service;

import java.util.Scanner;

import mentoring.service.mentee.MenteeLoginService;
import mentoring.service.mentor.MentorLoginService;

public class ChooseService {

	private int userSequence = -1;
	
	public void menu() {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n\n\n\n\n\n\n");
			System.out.println("=====초기 화면=====");
			System.out.println("1. 멘토");
			System.out.println("2. 멘티");
			System.out.println("3. 종료");
			System.out.println("=================");
			System.out.print("번호 :  ");
			int num = sc.nextInt();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
			if(num == 1) {
				MentorLoginService service = new MentorLoginService();
				service.execute(userSequence);
			}
			else if(num == 2) {
				MenteeLoginService service = new MenteeLoginService();
				service.execute(userSequence);
			}
			else if(num == 3) {
				sc.close();
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
				return;
			}
			else {
				System.out.println("1 ~ 3 중 선택하세요.");
			}
		
		}//while 끝
		
	}
	
	
}
