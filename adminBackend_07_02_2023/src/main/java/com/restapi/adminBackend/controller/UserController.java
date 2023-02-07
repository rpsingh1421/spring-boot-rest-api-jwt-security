package com.restapi.adminBackend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.adminBackend.Response.Response;
import com.restapi.adminBackend.payloads.UserDto;
import com.restapi.adminBackend.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	//@Value("${project.image}")///this is used when in application properties if it is declared as: project.image = path
	//private String path;
	//private String path = new ClassPathResource("static/Images/").getFile().getAbsolutePath();//this will give  absolute path
	private String path;
	public UserController() throws IOException {
		
	}
	@GetMapping("/last_user")
	public UserDto getLastUser() {
		return userService.getLastUser();
	}
	@PostMapping("/register")
	public Response addUser(@RequestBody UserDto userDto) {
		try {
			userService.addUser(userDto);
			return new Response(true,"Registered Successfully");
		} catch (Exception e) {
			return new Response(false,"!!Internal Server Error!!..Try Again Later");
		}
//		System.out.println(user);
//		return new Response(0,"!!Internal Server Error!!..Try Again Later");
	}
	
//	@PostMapping("/user_image_upload/{code}")
//	public Response uploadAadhar(@RequestParam("user_image") MultipartFile image, @PathVariable ("code") String code) {
//		String UPLOADED_FOLDER;
//		System.out.println(image.getOriginalFilename());
//		System.out.println(image.getSize());
//		System.out.println(image.getContentType());
//		System.out.println(image.getName());
//		if(image.isEmpty()) {
//			return new Response(0,"Request Must Contain file");
//		}
		
//		if(code =="aadhar") {
//			UPLOADED_FOLDER = "D://springboot_project//adminBackend//src//main//resources//static//Images//user_images//aadhar";
//			
//		}
//		else {
//			UPLOADED_FOLDER = "D://springboot_project//adminBackend//src//main//resources//static//Images//user_images//profile";
//			
//		}
		
//		try {
//			// Get the file and save it somewhere
//            byte[] bytes = image.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER+File.separator+image.getOriginalFilename());
//            Files.write(path, bytes);
//            userService.saveImage(image.getOriginalFilename(),code);
//			return new Response(1,code +"Uploaded Successfully");
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		return new Response(0,code+" Not Uploaded");
//	}

	@PostMapping("/user_image_upload/{code}")
	public Response uploadImage(@RequestParam("user_image") MultipartFile image, @PathVariable ("code") String code) {		
		
		
		if(code.equals("aadhar")) {
			//path = "D://springboot_project//adminBackend//src//main//resources//static//Images//user_images//aadhar";
			path = "src/main/resources/static/Images/user_images/aadhar";
		}
		else {
			path = "src/main/resources/static/Images/user_images/profile";
		}
		try {
            userService.uploadImage(path,image,code);
			return new Response(true,code +"Uploaded Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(false,code+" Not Uploaded");
		}	
		
		
	}
	
	//method to serve file
	@GetMapping(value="/aadhar_images/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException {
		path = "src/main/resources/static/Images/user_images/profile";
		InputStream resource = this.userService.getUserImage(path,imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	//localhost:8080/src/main/resources/static/Images/user_image/profile/abc.png
	
	@GetMapping("/get_users")
	public List<UserDto> getUsers(){
		return userService.getAllUsers();
	}
	@PutMapping("update/{id}")
	public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable ("id") long userId) {
		return this.userService.updateUser(userDto, userId)	;
	}
	
	@GetMapping("/get_user/{id}")
	public ResponseEntity<UserDto>  getUsers(@PathVariable ("id") long userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
