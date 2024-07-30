package mentoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class UserDTO {
	
	protected String id;
	protected String password;
	protected String email;
	protected String phone;

}
