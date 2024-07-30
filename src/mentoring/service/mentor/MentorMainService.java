package mentoring.service.mentor;

import java.util.Scanner;

import mentor.dao.MentorDAO;

public class MentorMainService implements MentorService {

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		MentorDAO dao = MentorDAO.getInstance();
		while (true) {
			Scanner sc = new Scanner(System.in);
			String mentee_name = dao.MentoringCheck(userSequence);
		//	String mentee_name = "멘티이름";
			if(mentee_name == null) {
				mentee_name = "없음";
				System.out.println("========메인화면[멘토]=======");
				System.out.println("(현재 연결된 멘티) : "+mentee_name);
				System.out.println("1. 멘티 요청 대기 목록");
				System.out.println("2. 이전");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					continue;
				}else if (num == 2) {
					return;
				} else {
					System.out.println("1 ~ 2만 선택하세요.");
				}
			}else {
				System.out.println("========메인화면[멘토]=======");
				System.out.println("(현재 연결된 멘티) : "+mentee_name);
				System.out.println("1. 멘티 상세 정보 보기");
				System.out.println("2. 멘토링 종료하기");
				System.out.println("3. 이전");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					continue;
				} else if (num == 2) {
					/*
					int result = dao.get(userSequence);
					if(result == 1) {
						System.out.println("멘토링이 종료되었습니다.");
					}
					else {
						System.out.println("오류 : 삭제 실패");
					}*/
				} else if (num == 3) {
					return;
				} else {
					System.out.println("1 ~ 3만 선택하세요.");
				}
			}
		}
	}

}
