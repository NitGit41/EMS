package employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}
