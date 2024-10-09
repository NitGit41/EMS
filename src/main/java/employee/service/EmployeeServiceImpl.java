package employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dto.EmployeeDto;
import employee.exception.ResourceNotFoundException;
import employee.model.Employee;
import employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EmployeeRepository repo;

	@Override
	public String addEmployee(EmployeeDto e) {
		Employee emp = mapper.map(e, Employee.class);
		repo.save(emp);
		return "Employee is added successfuly";
	}

	@Override
	public EmployeeDto getEmployee(long id) {
		Optional<Employee> opt = repo.findById(id);
		Employee e = opt.orElseThrow(() -> new ResourceNotFoundException("Record Not Found"));
		EmployeeDto emp = mapper.map(e, EmployeeDto.class);
		return emp;
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> list = repo.findAll();
		List<EmployeeDto> empList = new ArrayList<>();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Record Not Found");
		} else {
			list.forEach(emp -> empList.add(mapper.map(emp, EmployeeDto.class)));
			return empList;
		}
	}

	@Override
	public String deleteEmployee(long id) {
		Optional<Employee> opt = repo.findById(id);
		if (opt.isPresent()) {
			repo.deleteById(id);
			return "Employee record deleted";
		} else {
			throw new ResourceNotFoundException("Record Not Found");
		}
	}

	@Override
	public String deleteAllEmployee() {
		List<Employee> list = repo.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Record Not Found");
		} else {
			repo.deleteAll();
			return "All Employee record deleted";
		}
	}

}
