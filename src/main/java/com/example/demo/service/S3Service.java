package com.example.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	
	String uploadFileToSpecificBucket(MultipartFile uploadfile) throws IOException;
	
	byte[] downloadFile(String fileName) throws IOException;

}
