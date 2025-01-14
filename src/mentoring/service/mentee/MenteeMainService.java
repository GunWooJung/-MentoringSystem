package mentoring.service.mentee;

import java.util.List;
import java.util.Scanner;

import mentee.dao.MenteeDAO;
import mentoring.dto.MentorDTO;
import mentoring.dto.ReviewDTO;

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
				System.out.println("      ╔════════════ 메인화면[멘티] ════════════╗");
				System.out.println("              ◈ 현재 연결된 멘토 : " + mentor_name);
				System.out.println();
				System.out.println("                 1. 멘토링 신청 ");
				System.out.println("                 2. 신청 대기 중인 목록");
				System.out.println("                 3. 종료된 멘토 리뷰 작성");
				System.out.println("                 4. 모든 리뷰 보기");
				System.out.println("                 5. 이전");
				System.out.println("                 6. 종료");
				System.out.print("               ◈ 번호 :  ");
				int num = sc.nextInt();
				System.out.println("      ╚═════════════════════════════════════╝");
				System.out.println("\n\n");
				System.out.println("-----------------------------------------------");	
				System.out.println("\n\n");

				if (num == 1) {
					System.out.println("╔═════════════════════════════════════════════════════╗");
					System.out.println("            ◈ 어떤 분야로 조회하시겠습니까?");
					System.out.println("            1.프론트엔드 2. 백엔드 3. 네트워크 \n"
					                        + "\t\t4. 클라우드 5. 모든 분야");
					System.out.print("             ◈ 번호 입력 : ");
					int department = sc.nextInt();
					System.out.println("\n\n");
				
					if(department==1) {
						System.out.println(" ------------------ 프론트엔드 ------------------");
					}
					else if(department==2) {
						System.out.println(" ------------------ 백엔드 ------------------");
					}
					else if(department==3) {
						System.out.println(" ------------------ 네트워크 ------------------");
					}
					else if(department==4) {
						System.out.println(" ------------------ 클라우드 ------------------");
					}
					else if(department==5) {
						System.out.println(" ------------------ 모든 분야 ------------------");
					}
					System.out.println("             멘토번호\t이름\t분야");
					System.out.println("-----------------------------------------------");
					List<MentorDTO> list = dao.DepartmenSort(department);
					if(list.size()==0) {
						System.out.println("             ※ 등록된 멘토가 없습니다.※");
						System.out.println("\n\n");
						continue;
					}
					for(MentorDTO dto : list) {
						System.out.println("\t\t" + dto.getMentor_seq()+"\t"+dto.getName()+"\t"+
								dto.getDepartment());
					}
					System.out.print("         신청할 멘토 번호를 입력(뒤로가기 : 0) : ");
					int mentor_seq = sc.nextInt();
					if(mentor_seq == 0) {
						continue;
					}
					int result = dao.MentoringRequest(userSequence, mentor_seq);	
					if(result == 1) {
						System.out.println("         ♥ 멘토링이 신청(대기상태)되었습니다. ♥");
						System.out.println("╚═════════════════════════════════════════════════════╝");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					else if(result == -1) {
						System.out.println("           해당 멘토에게 이미 신청되었습니다.");
						System.out.println("╚═════════════════════════════════════════════════════╝");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					else {
						System.out.println("            등록 실패 : 오류가 발생했습니다.");
						System.out.println("╚═════════════════════════════════════════════════════╝");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
				} else if (num == 2) {
					
					List<MentorDTO> list = dao.RequestList(userSequence);
					if(list.size() == 0) {
						System.out.println("              신청 대기 목록이 없습니다.");
						continue;
						
					}
					
					for(MentorDTO dto : list) {
						System.out.println("      ╔════════════════════════════════════════╗");
						System.out.println("\t\t멘토번호\t이름");
						System.out.println("\t\t" + dto.getMentor_seq()+"\t"+dto.getName());
					}
					System.out.print("       ◈ 취소할 멘토번호를 입력하세요.(뒤로가기 : 0) : ");
					int mentee_seq = sc.nextInt();
					System.out.println("     ╚═════════════════════════════════════════╝");
					System.out.println("\n\n");
					System.out.println("-----------------------------------------------");	
					System.out.println("\n\n");
					if(mentee_seq == 0) {
						continue;
					}
					int result = dao.RequestCancell(userSequence, mentee_seq);
					if(result == 1) {
						System.out.println("             ★ 멘토링이 취소되었습니다. ★");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					else {
						System.out.println("오류 : 취소 실패");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					
				} else if (num == 3) {
					List<MentorDTO> list = dao.ReviewPossibility(userSequence);
					if(list.size() == 0) {
						System.out.println("멘토링이 종료된 목록이 없습니다.");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					else {
						System.out.println("      ╔════════════════════════════════════════╗");
						System.out.println("            ◈ 리뷰를 작성할 멘토 번호를 선택하세요.");
						System.out.println("                  멘토 번호\t멘토 이름");
						for(MentorDTO dto : list) {
							System.out.println("\t\t\t" + dto.getMentor_seq()+"\t"+dto.getName());
						}
						System.out.println();
						System.out.print("                 ◈ 멘티 번호 : ");
						int mentor_seq = sc.nextInt();
						sc.nextLine();
						System.out.print("             ◈  리뷰 내용 : ");
						String review = sc.nextLine();
						int result = dao.reviewWrite(userSequence, mentor_seq , review);
						if(result == 1) {
							System.out.println("                 ♥ 리뷰가 작성되었습니다. ♥");
							System.out.println("      ╚═════════════════════════════════════════╝");
						}
						else {
							System.out.println("                 ※ 오류 : 리뷰 작성 실패!");
							System.out.println("      ╚═════════════════════════════════════════╝");
						}
					}

				} else if (num == 4) {
					//리뷰 목록
					List<ReviewDTO> list = dao.reviewList();
					if(list == null) {
						System.out.println("등록된 리뷰가 없습니다.");
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
					else {
						for(ReviewDTO dto : list) {
							System.out.println("      ╔════════════════════════════════════════╗");
							System.out.println("              ◈ 멘토 번호 : "+dto.getMentor_seq());
							System.out.println("              ◈ 멘토이름 : " + dto.getName());
							System.out.println("              ◈ 리뷰 내용 : " +dto.getReview());
							System.out.println("      ╚════════════════════════════════════════╝");
							System.out.println();						
						}
						System.out.println("\n\n");
						System.out.println("-----------------------------------------------");	
						System.out.println("\n\n");
					}
				} else if (num == 5) {
					return;
				} else if (num == 6) {
					System.out.println("  _______ _    _ ______   ______ _   _ _____  \r\n"
							+ " |__   __| |  | |  ____| |  ____| \\ | |  __ \\ \r\n"
							+ "    | |  | |__| | |__    | |__  |  \\| | |  | |\r\n"
							+ "    | |  |  __  |  __|   |  __| | . ` | |  | |\r\n"
							+ "    | |  | |  | | |____  | |____| |\\  | |__| |\r\n"
							+ "    |_|  |_|  |_|______| |______|_| \\_|_____/");
					System.exit(0);
				}
				else {
					System.out.println("1 ~ 6만 선택하세요.");
				}
			} else {
				System.out.println("      ╔════════════ 메인화면[멘티] ════════════╗");
				System.out.println("              ◈ 현재 연결된 멘토 : " + mentor_name);
				System.out.println("              1. 멘토 상세 정보 보기 ");
				System.out.println("              2. 모든 리뷰 보기");
				System.out.println("              3. 이전");
				System.out.println("              4. 종료");
				System.out.print("               ◈ 번호 :  ");
				int num = sc.nextInt();
				System.out.println("      ╚═════════════════════════════════════╝");
				System.out.println("\n\n");

				if (num == 1) {
					MentorDTO dto = dao.Mentorinformtion(userSequence);
					System.out.println("         이름\t분야\t전화번호\t이메일");
					System.out.println("-----------------------------------------------");
					System.out.println("\t" + dto.getName()+"\t"+dto.getDepartment()
							+"\t"+dto.getPhone()+"\t"+dto.getEmail());
					System.out.println("-----------------------------------------------");	
					System.out.println("\n\n");
				}else if (num == 2) {
					List<ReviewDTO> list = dao.reviewList();
					if(list == null) {
						System.out.println("등록된 리뷰가 없습니다.");
					}
					else {
						for(ReviewDTO dto : list) {
							System.out.println("멘토 번호 : "+dto.getMentor_seq()+
												"\n멘토이름 : "+dto.getName()+
												"\n리뷰 내용 : "+dto.getReview());
							System.out.println();						
						}
					}
				}else if (num == 3) {
					return;
				} else if (num == 4) {
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
			}
		}
	}

}
