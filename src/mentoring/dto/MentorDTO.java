package mentoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentorDTO extends UserDTO{
	private int mentor_seq;
	private String department;
	private int status;
	
	@Override
    public String toString() {
        return "MenteeDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
