package com.restapi.adminBackend.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.restapi.adminBackend.entities.User;
import com.restapi.adminBackend.payloads.UserDto;

public interface UserService {

//	public User getLastUser();
//
//	public void addUser(User user);
//
////	public void saveImage(String originalFilename, String code);
//
	public void uploadImage(String path, MultipartFile image, String code) throws IOException;
	public InputStream getUserImage(String path, String fileName) throws FileNotFoundException;
//
//	public List<User> getUsers();

	UserDto addUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, long userId);
	UserDto getUserById(long userId);
	List<UserDto> getAllUsers();
	void deleteUser(long userId);
	public UserDto getLastUser();

}
