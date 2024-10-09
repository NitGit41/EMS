package employee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "New User")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

}
