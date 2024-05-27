package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
	List<User> getAllUsers();
	
	List<User> getUsersByPagination(int pageNo, int pageSize);
	
	Integer getUsersCount();
	
	User getUserById(Long id);
	
	User saveUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(Long id);

	String uploadImage(MultipartFile image) throws IOException;
	
	String updateImage(String oldImageName, MultipartFile image) throws IOException;
	
	String encryptPassword(String password);
	
	
}
