package mentoring.service.mentor;

import java.util.Scanner;

import mentee.dao.MenteeDAO;
import mentor.dao.MentorDAO;
import mentoring.dto.MentorDTO;
import mentoring.service.mentee.MenteeMainService;
import mentoring.service.mentee.MenteeService;

public class MentorLoginService implements MentorService {

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("             ╔═══════ 멘토 ═══════╗");
			System.out.println("                    1. 로그인");
			System.out.println("                    2. 회원 가입");
			System.out.println("                    3. 이전");
			System.out.println("                    4. 종료");
			System.out.print("                  ◈ 번호 :  ");
			int num = sc.nextInt();
			System.out.println("             ╚═══════════════════╝");
			System.out.println("\n\n");
			System.out.println("-----------------------------------------------");	
			System.out.println("\n\n");
			MentorService service = null;

			MentorDAO dao = MentorDAO.getInstance();

			if (num == 1) {
				System.out.println("      ╔═══════════════════════════════════╗");
				System.out.print("                 ◈ 아이디 : ");
				String id = sc.next();
				System.out.print("                 ◈ 비밀번호 : ");
				String pwd = sc.next();

				int menetor_seq = dao.Login(id, pwd);
				if (menetor_seq == -1) {// 로그인 실패
					System.out.println();
					System.out.println("       ※아이디 또는 비밀 번호가 일치하지 않습니다.");
					System.out.println("      ╚═══════════════════════════════════╝");
					System.out.println("-----------------------------------------------");	
					System.out.println("\n\n");
					continue;
				} else { // 정상
					System.out.println("      ╚═══════════════════════════════════╝");
					System.out.println();
					System.out.println("-----------------------------------------------");	
					System.out.println("\n\n");
					//System.out.println(menetor_seq);
					service = new MentorMainService();
					service.execute(menetor_seq);
				}
			} else if (num == 2) {

				System.out.println("          ╔═══════════════════════════╗");
				System.out.print("                ◈ 아이디 : ");
				String id = sc.next();
				boolean AlreadyId = dao.IdCheck(id);
				if (AlreadyId == true) {
					System.out.println("            ※이미 존재하는 아이디입니다.");
					System.out.println("          ╚════════════════════════════╝");
					System.out.println("\n\n");
					System.out.println("-----------------------------------------------");	
					System.out.println("\n\n");
					continue;
				} else {

					System.out.print("                ◈ 비밀번호 : ");
					String pwd = sc.next();
					System.out.print("                ◈ 이름 : ");
					String name = sc.next();
					System.out.println("                ◈ 현직 분야 ");
					System.out.println("             1. 프론트엔드  2. 백엔드 ");
					System.out.println("             3. 네트워크    4. 클라우드");
					System.out.print("                → 번호 선택 : ");
					int departmentNum = sc.nextInt();
					String department = null;

					switch (departmentNum) {
					case 1:
						department = "프론트엔드";
						break;
					case 2:
						department = "백엔드";
						break;
					case 3:
						department = "네트워크";
						break;
					case 4:
						department = "클라우드";
						break;
					}
					if (department == null) {
						System.out.println("1~4를 선택하세요.");
						continue;
					}
					System.out.print("             ◈ 이메일 : ");
					String email = sc.next();
					System.out.print("             ◈ 핸드폰 번호 : ");
					String phone = sc.next();

					MentorDTO dto = new MentorDTO();
					dto.setName(name);
					dto.setId(id);
					dto.setPwd(pwd);
					dto.setDepartment(department);
					dto.setEmail(email);
					dto.setPhone(phone);
					dto.setStatus(0);
					int count = dao.Join(dto);
					if (count == 1) {
						System.out.println();
						System.out.println("             ♥ 회원 가입에 성공하였습니다.♥ ");
						System.out.println("          ╚════════════════════════════╝");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					} else {
						System.out.println("             ♥ 회원 가입에 실패하였습니다.♥ ");
						System.out.println("          ╚════════════════════════════╝");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
				} // while
			} else if (num == 3) {
				return;
			} else if (num ==4) {
				System.out.println("  _______ _    _ ______   ______ _   _ _____  \r\n"
						+ " |__   __| |  | |  ____| |  ____| \\ | |  __ \\ \r\n"
						+ "    | |  | |__| | |__    | |__  |  \\| | |  | |\r\n"
						+ "    | |  |  __  |  __|   |  __| | . ` | |  | |\r\n"
						+ "    | |  | |  | | |____  | |____| |\\  | |__| |\r\n"
						+ "    |_|  |_|  |_|______| |______|_| \\_|_____/");
				System.exit(0);
			}
			else {
				System.out.println("1 ~ 4만 선택하세요.");
				System.out.println("\n\n");
				System.out.println("-----------------------------------------------");		
			}

		} // while 끝
	}

}
