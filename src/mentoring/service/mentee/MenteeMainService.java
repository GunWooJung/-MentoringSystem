package mentoring.service.mentee;

import java.util.Scanner;

public class MenteeMainService implements MenteeService{

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("========메인화면[멘티]=======");
		System.out.println("1. ");
		System.out.println("2. ");
		System.out.println("3. 이전");
		System.out.println("=================");
		System.out.print("번호 :  ");
		int num = sc.nextInt();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
		
		if(num == 1) {

		}
		else if(num == 2) {
			
			
		}
		else if(num == 3) {
			return;
		}
		else {
			System.out.println("1 ~ 3만 선택하세요.");
		}
	}

}
