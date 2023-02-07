package com.restapi.adminBackend.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.restapi.adminBackend.services.FileService;

@Service
public class FileServiceimpl implements FileService{
	
	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullpath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullpath);
		//db logic to return inputstream 
		return is;
	}
}
