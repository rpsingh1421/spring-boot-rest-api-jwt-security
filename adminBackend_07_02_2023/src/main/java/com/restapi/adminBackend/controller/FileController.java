package com.restapi.adminBackend.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.adminBackend.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class FileController {
	private String path;
	@Autowired
	private FileService fileService;
	@GetMapping(value="/get_Image/{aadhar}/{image}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void serveAadhar(@PathVariable("image") String imageName, @PathVariable("aadhar") String code,HttpServletResponse response) throws IOException {
		boolean f =false;
		if (code.equals("aadhar")) {
			path = "src/main/resources/static/Images/user_images/aadhar";
			f=true;
		}
		if (code.equals("profile")) {
			path = "src/main/resources/static/Images/user_images/profile";
			f=true;
			
		}
		if(f) {
			InputStream resource= fileService.getImage(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource,response.getOutputStream());
		}
	}

//	@GetMapping(value="/aadhar_images/{imageName}", proMediaType.IMAGE_JPEG_VALUE)
//	public void serveImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException {
//		path = "src/main/resources/static/Images/user_images/profile";
//		InputStream resource = this.userService.getUserImage(path,imageName);
//		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//		StreamUtils.copy(resource,response.getOutputStream());
//	}
}