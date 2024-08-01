package mentoring.main;

import mentee.dao.MenteeDAO;
import mentoring.dto.MenteeDTO;
import mentoring.service.ChooseService;

public class IndexMain {

	public static void main(String[] args) {

		ChooseService service = new ChooseService();
		service.menu();
		System.out.println("  _______ _    _ ______   ______ _   _ _____  \r\n"
				+ " |__   __| |  | |  ____| |  ____| \\ | |  __ \\ \r\n"
				+ "    | |  | |__| | |__    | |__  |  \\| | |  | |\r\n"
				+ "    | |  |  __  |  __|   |  __| | . ` | |  | |\r\n"
				+ "    | |  | |  | | |____  | |____| |\\  | |__| |\r\n"
				+ "    |_|  |_|  |_|______| |______|_| \\_|_____/");
		
		

	}
}

