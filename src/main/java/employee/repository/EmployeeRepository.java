package employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
