package employee.service;

import employee.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
	String addEmployee(EmployeeDto e);

	EmployeeDto getEmployee(long id);

	List<EmployeeDto> getAllEmployee();

	String deleteEmployee(long id);

	String deleteAllEmployee();

}
