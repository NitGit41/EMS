package employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import employee.dto.UserDto;
import employee.model.User;
import employee.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository repo;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public String registerUser(UserDto user) {
		if (repo.findByUsername(user.getUsername()) == null) {
			User u = mapper.map(user, User.class);
			u.setPassword(encoder.encode(user.getPassword()));
			repo.save(u);
			return "User registered";

		} else {
			return "User already exist";
		}
	}

}
