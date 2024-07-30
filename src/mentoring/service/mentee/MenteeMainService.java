package mentoring.service.mentee;

import java.util.Scanner;

import mentee.dao.MenteeDAO;

public class MenteeMainService implements MenteeService {

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		MenteeDAO dao = MenteeDAO.getInstance();

		while (true) {
			Scanner sc = new Scanner(System.in);
			String mentor_name = dao.MentoringCheck(userSequence);
			//String mentor_name = "연결된 멘토";
			if (mentor_name == null) {
				mentor_name = "없음";
				System.out.println("========메인화면[멘티]=======");
				System.out.println("(현재 연결된 멘토) : " + mentor_name);
				System.out.println("1. 멘토링 신청 ");
				System.out.println("2. 신청 대기 중인 목록");
				System.out.println("3. 이전");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					continue;
				} else if (num == 2) {
					continue;
				} else if (num == 3) {
					return;
				} else {
					System.out.println("1 ~ 3만 선택하세요.");
				}
			} else {
				mentor_name = "없음";
				System.out.println("========메인화면[멘티]=======");
				System.out.println("(현재 연결된 멘토) : " + mentor_name);
				System.out.println("1. 멘토 상세 정보 보기 ");
				System.out.println("2. 이전");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					continue;
				} else if (num == 2) {
					return;
				}else {
					System.out.println("1 ~ 2만 선택하세요.");
				}
			}
		}
	}

}
