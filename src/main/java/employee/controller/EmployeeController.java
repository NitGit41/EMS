package employee.controller;

import org.springframework.web.bind.annotation.RestController;

import employee.dto.EmployeeDto;
import employee.service.EmployeeServiceImpl;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl service;

	@PostMapping("/add/employee")
	public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeDto e) {
		String response = service.addEmployee(e);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/get/employee/") // @RequestParam(value = "id", defaultValue = "1") default value for id(key) can
									// be provided also
	public ResponseEntity<EmployeeDto> getEmployee(@RequestParam(value = "id") long id) {
		EmployeeDto emp = service.getEmployee(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@GetMapping("/get/all/employee")
	// provided also
	public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
		List<EmployeeDto> emp = service.getAllEmployee();
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@DeleteMapping("delete/employee")
	public ResponseEntity<String> deleteEmployee(@RequestParam(value = "id") long id) {
		String response = service.deleteEmployee(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("delete/all/employee")
	public ResponseEntity<String> deleteAllEmployee() {
		String response = service.deleteAllEmployee();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
