package mentoring.service.mentor;

import java.util.List;
import java.util.Scanner;

import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;
import mentoring.dto.MentorDTO;

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
				System.out.println("3. 종료");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					System.out.println("멘티 번호\t이름");
					System.out.println("------------------");
					List<MenteeDTO> list = dao.MenteeList(userSequence);
					if(list.size() == 0) {
						System.out.println("신청 대기 목록이 없습니다.");
						continue;
					}
					for(MenteeDTO dto : list) {
						System.out.println(dto.getMentee_seq()+"\t"+dto.getName());
					}
					System.out.print("승인할 멘토 번호를 입력하세요.");
					int mentee_seq = sc.nextInt();
					boolean result = dao.MentoringAccept(userSequence, mentee_seq);
					if(result == true) {
						System.out.println("멘토링이 승인되었습니다.");
					}
					else {
						System.out.println("오류 : 승인 실패");
					}
	
				}else if (num == 2) {
					return;
				} else if (num == 3) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
				else {
					System.out.println("1 ~ 3만 선택하세요.");
				}
			}else {
				System.out.println("========메인화면[멘토]=======");
				System.out.println("(현재 연결된 멘티) : "+mentee_name);
				System.out.println("1. 멘티 상세 정보 보기");
				System.out.println("2. 멘토링 종료하기");
				System.out.println("3. 이전");
				System.out.println("4. 종료");
				System.out.println("=================");
				System.out.print("번호 :  ");
				int num = sc.nextInt();
				System.out.println("\n\n");

				if (num == 1) {
					MenteeDTO dto = dao.MenteeInformation(userSequence);
					System.out.println("이름\t전화번호\t이메일");
					System.out.println("------------------");
					System.out.println(dto.getName()+"\t"
							+dto.getPhone()+"\t"+dto.getEmail());
				} else if (num == 2) {
				
					boolean result = dao.MentoringEnd(userSequence);
					if(result == true) {
						System.out.println("멘토링이 종료되었습니다.");
					}
					else {
						System.out.println("오류 : 삭제 실패");
					}
					
				} else if (num == 3) {
					return;
				}else if (num ==4) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
				else {
					System.out.println("1 ~ 4만 선택하세요.");
				}
			}
		}
	}

}
