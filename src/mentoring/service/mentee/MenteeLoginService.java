package mentoring.service.mentee;

import java.util.Scanner;

import mentee.dao.MenteeDAO;
import mentor.dao.MentorDAO;
import mentoring.dto.MenteeDTO;
import mentoring.dto.MentorDTO;
import mentoring.service.mentor.MentorMainService;
import mentoring.service.mentor.MentorService;

public class MenteeLoginService implements MenteeService {

	@Override
	public void execute(int userSequence) {
		// TODO Auto-generated method stub
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("             ╔═══════ 멘티 ═══════╗");
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
			MenteeService service = null;

			MenteeDAO dao = MenteeDAO.getInstance();

			if (num == 1) {
				System.out.println("      ╔═══════════════════════════════════╗");
				System.out.print("                 ◈ 아이디 : ");
				String id = sc.next();
				System.out.print("                 ◈ 비밀번호 : ");
				String pwd = sc.next();

				int menetee_seq = dao.Login(id, pwd);
				if (menetee_seq == -1) {// 로그인 실패
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
					service = new MenteeMainService();
					service.execute(menetee_seq);
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
					System.out.print("             ◈ 이메일 : ");
					String email = sc.next();
					System.out.print("             ◈ 핸드폰 번호 : ");
					String phone = sc.next();
					MenteeDTO dto = new MenteeDTO();
					dto.setName(name);
					dto.setId(id);
					dto.setPwd(pwd);
					dto.setEmail(email);
					dto.setPhone(phone);
					int count = dao.Join(dto);
					if (count == 1) {
						System.out.println("             ♥ 회원 가입에 성공하였습니다.♥ ");
						System.out.println("          ╚════════════════════════════╝");
					} else {
						System.out.println("             ♥ 회원 가입에 실패하였습니다.♥ ");
						System.out.println("          ╚════════════════════════════╝");
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
				System.out.println("              ※1 ~ 4만 선택하세요.※");
				System.out.println("\n\n");
				System.out.println("-----------------------------------------------");		
			}

		} // while 끝
	}

}
