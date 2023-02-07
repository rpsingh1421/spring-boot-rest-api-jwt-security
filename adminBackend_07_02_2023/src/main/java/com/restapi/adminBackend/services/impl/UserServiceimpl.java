package com.restapi.adminBackend.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.adminBackend.dao.UserDao;
import com.restapi.adminBackend.entities.User;
import com.restapi.adminBackend.exceptions.customExceptions.EmptyResourceException;
import com.restapi.adminBackend.exceptions.customExceptions.ResourceNotFoundException;
import com.restapi.adminBackend.payloads.UserDto;
import com.restapi.adminBackend.services.UserService;

@Service
public class UserServiceimpl implements UserService{

	@Autowired
	private UserDao userDao;

//	@Override
//	public long getLastId() {
//		//userDao.getLastId();
//		String  last_id = userDao.getLastId();
//		if (last_id != null) {
//			return Integer.parseInt(last_id) + 1;
//		} else {
//			return 1;
//		}
//		
//	}
	



//	@Override
//	public void saveImage(String originalFilename, String code) {
//		// TODO Auto-generated method stub
//		User user=getLastUser();
//		if (code == "aadhar") {
//			user.setAadhar_img(originalFilename);
//		} else {
//			user.setProfile_img(originalFilename);
//		}
//		userDao.save(user);
//	}


	@Override
	public void uploadImage(String path, MultipartFile file, String code) throws IOException {
		//File Name
		String name = file.getOriginalFilename();
		//Fullpath
		String filepath = path+File.separator+name;
		//create Folder if not created
		System.out.println(path);
		System.out.println(filepath);
		File file1= new File(path);
		if (!file1.exists()) {
			file1.mkdir();
		}
		 
		//File Copy
		
		Files.copy(file.getInputStream(), Paths.get(filepath));
		//save to Database
		User user=userDao.getLastUser();
		if (code.equals("aadhar")) {
			user.setAadhar_img(name);
		} else {
			user.setProfile_img(name);
		}
		userDao.save(user);
		
	}


	@Override
	public InputStream getUserImage(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullpath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullpath);
		//db logic to return inputstream 
		return is;
	}




	@Override
	public UserDto addUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		user.setAadhar_img("deault_aadhar.jpg");
		user.setProfile_img("default_profile.jpg");
		User savedUser=userDao.save(user);
		UserDto saveduserDto = this.userToDto(savedUser);
		return saveduserDto;
	}


	@Override
	public UserDto updateUser(UserDto userDto, long userId) {
		
		User existUser = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		User updateUser = this.dtoToUser(userDto);
		User updatedUser = userDao.save(updateUser);
		
		return this.userToDto(updatedUser);
	}


	@Override
	public UserDto getUserById(long userId) {
		User existUser = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		return this.userToDto(existUser);
	}


	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userDao.findAll();
		
		if (users.isEmpty()) {
			throw new EmptyResourceException("604","Required List is Empty");
		}
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}


	@Override
	public void deleteUser(long userId) {
		User user =  this.userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userDao.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setRoleCode(userDto.getRoleCode());
		user.setName(userDto.getName());
		user.setContact1(userDto.getContact1());
		user.setContact2(userDto.getContact2());
		user.setEmail(userDto.getEmail());
		user.setPass(userDto.getPass());
		user.setAadhar_img(userDto.getAadhar_img());
		user.setAadhar_no(userDto.getAadhar_no());
		user.setCity(userDto.getCity());
		user.setAddress(userDto.getAddress());
		user.setDist(userDto.getDist());
		user.setPincode(userDto.getPincode());
		user.setProfile_img(userDto.getProfile_img());
		user.setStatus(userDto.getStatus());
		return user;
		
	}
	public UserDto userToDto (User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setRoleCode(user.getRoleCode());
		userDto.setName(user.getName());
		userDto.setContact1(user.getContact1());
		userDto.setContact2(user.getContact2());
		userDto.setEmail(user.getEmail());
		userDto.setPass(user.getPass());
		userDto.setAadhar_img(user.getAadhar_img());
		userDto.setAadhar_no(user.getAadhar_no());
		userDto.setCity(user.getCity());
		userDto.setAddress(user.getAddress());
		userDto.setDist(user.getDist());
		userDto.setPincode(user.getPincode());
		userDto.setProfile_img(user.getProfile_img());
		userDto.setStatus(user.getStatus());
		return userDto;
		
	}


	@Override
	public UserDto getLastUser() {
		// TODO Auto-generated method stub
		User lastUser = this.userDao.getLastUser();
		return this.userToDto(lastUser);
	}


	

}
