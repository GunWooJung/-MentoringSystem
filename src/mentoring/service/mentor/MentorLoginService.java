package mentoring.service.mentor;

import java.util.Scanner;

import mentoring.service.mentee.MenteeMainService;
import mentoring.service.mentee.MenteeService;

public class MentorLoginService implements MentorService {

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("========멘토=======");
			System.out.println("1. 로그인");
			System.out.println("2. 회원 가입");
			System.out.println("3. 이전");
			System.out.println("=================");
			System.out.print("번호 :  ");
			int num = sc.nextInt();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
			MentorService service = null;
			if(num == 1) {
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀 번호 : ");
				String password = sc.next();
				
				service = new MentorMainService();
				service.execute(userSequence);
			}
			else if(num == 2) {
				
				
			}
			else if(num == 3) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
				return;
			}
			else {
				System.out.println("1 ~ 3만 선택하세요.");
			}
			
		}//while 끝
	}

}
