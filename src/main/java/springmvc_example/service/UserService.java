package springmvc_example.service;

import java.util.List;

import springmvc_example.model.User;

public interface UserService {
	public List<User> listAllUser();

	public void addUser(User user);

	public void deleteUser(int id);

	public void updateUser(User user);

	public User findById(int id);

}
