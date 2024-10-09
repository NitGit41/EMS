package employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private long id;

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	private long phone;

	@NotBlank
	private String bloodGroup;

}
