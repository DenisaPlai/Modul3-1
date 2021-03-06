package com.application.modul3.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.modul3.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
//		Optional<User> userOpt = userRepository.findById(id);
//		if (userOpt.isPresent()) {
//			return userOpt.get();
//		}
//		return null;
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
	}

	public void deleteUserById(Integer id) {
		getUserById(id);
		userRepository.deleteById(id);
	}

	// tr sa verificam daca user-ul cu id respectiv este in baza noastra de date
	public User updateUser(User user, Integer id) {
		User userUpdate = getUserById(id);
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setMail(user.getMail());
		userUpdate.setAddress(user.getAddress());
		userRepository.flush();
		return userUpdate;

	}
}
