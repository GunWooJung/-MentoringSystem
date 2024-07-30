package mentoring.service.mentor;

import java.util.Scanner;

import mentee.dao.MenteeDAO;
import mentoring.dto.MentorDTO;
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
			System.out.println("\n\n");
			MentorService service = null;
			if(num == 1) {
				while(true) {
					System.out.print("아이디 : ");
					String id = sc.next();
					System.out.print("비밀 번호 : ");
					String pwd = sc.next();
					MenteeDAO dao = dao.getInstance();
					int menetor_seq = dao.Login(id, pwd);
					if(menetor_seq == -1) {//로그인 실패
						System.out.println("아이디 또는 비밀 번호가 일치하지 않습니다.\n");
					}
					else { //정상
						service = new MentorMainService();
						service.execute(userSequence);
					}
				}
			}
			else if(num == 2) {
				
				while(true) {
					MenteeDAO dao = dao.getInstance();
					System.out.print("아이디 : ");
					String id = sc.next();
					boolean AlreadyId = dao.IdCheck("아이디 테스트");
					if(AlreadyId == true) {
						System.out.println("이미 존재하는 아이디입니다.");
					}
					else {
						MentorDTO dto = new MentorDTO();
						System.out.print("비밀 번호 : ");
						String pwd = sc.next();
						System.out.print("비밀 번호 : ");
						String pwd = sc.next();
						System.out.print("현직 분야(숫자 선택) : ");
						System.out.println("1. 프론트엔드. 2. 백엔드 3. 네트워크 "+
											"4. 클라우드");
						int departmentNum = sc.nextInt();
						String department = "";
						switch(departmentNum) {
						case 1: department = "프론트엔드"; break;
						case 2: department = "백엔드"; break;
						case 3: department = "네트워크"; break;
						case 4: department = "클라우드"; break;
						
						}
						
						
						dto.setId(id);
						
						
						
					}
				}//while
			}
			else if(num == 3) {
				return;
			}
			else {
				System.out.println("1 ~ 3만 선택하세요.");
			}
			
		}//while 끝
	}

}
