package com.restapi.adminBackend.services;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileService {

	InputStream getImage(String path, String fileName) throws FileNotFoundException;

	

}
