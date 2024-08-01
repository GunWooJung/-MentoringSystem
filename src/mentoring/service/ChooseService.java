package mentoring.service;

import java.util.Scanner;

import mentoring.service.mentee.MenteeLoginService;
import mentoring.service.mentor.MentorLoginService;

public class ChooseService {

	private int userSequence = -1;
	
	public void menu() {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("  __  __ ___ _  _ _____ ___  ___ ___ _  _  ___ \r\n"
					+ " |  \\/  | __| \\| |_   _/ _ \\| _ \\_ _| \\| |/ __|\r\n"
					+ " | |\\/| | _|| .` | | || (_) |   /| || .` | (_ |\r\n"
					+ " |_|  |_|___|_|\\_| |_| \\___/|_|_\\___|_|\\_|\\___|\r\n"
					+ "                                               ");
			System.out.println("      ╔════════════ 초기 화면 ════════════╗");
			System.out.println("                    1. 멘토");
			System.out.println("                    2. 멘티");
			System.out.println("                    3. 종료");
			System.out.println("      ╚═════════════════════════════════╝");
			System.out.print("                  ◈ 번호 :  ");
			int num = sc.nextInt();
			System.out.println("\n\n");
			System.out.println("-----------------------------------------------");		
			System.out.println("\n\n");
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
				return;
			}
			else {
				System.out.println("              ※1 ~ 3 중 선택하세요.※");
				System.out.println("\n\n");
				System.out.println("-----------------------------------------------");		
			}
		
		}//while 끝
		
	}
	
	
}
