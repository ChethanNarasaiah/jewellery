package com.example.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.demo.aws.config.AwsConfiguration;
import com.example.demo.service.S3Service;

@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private AwsConfiguration awsConfiguration;

	private static final String SUFFIX = "/";

	@Override
	public String uploadFileToSpecificBucket(MultipartFile uploadfile) throws IOException {

		String bucketName = "myjewels";
		System.out.println("BucketName : " + bucketName);

		String folderName = "product";
		System.out.println("FolderName : " + folderName);

		AmazonS3 s3Client = awsConfiguration.s3Configuration();
		String generatedfileName = generateFileName(uploadfile);
		String fileName = folderName + SUFFIX + generatedfileName;
		System.out.println("FileName : " + fileName);

		s3Client.putObject(new PutObjectRequest(bucketName, fileName, convertMultiPartToFile(uploadfile))
				.withCannedAcl(CannedAccessControlList.PublicRead));

		System.out.println("<----------File Uploaded Successfully------------>");
		return "File Uploaded Successfully";
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		// return new Date() + "-" + multiPart.getOriginalFilename().replace("
		// ", "_");
		return multiPart.getOriginalFilename().replace(" ", "_");
	}

	@Override
	public byte[] downloadFile(String fileName) throws IOException {
		AmazonS3 s3Client = awsConfiguration.s3Configuration();
		String bucketName = "myjewels";
		String folderName = "product";
		String files = folderName + SUFFIX + fileName;

		GetObjectRequest request = new GetObjectRequest(bucketName, files);
		S3Object s3object = s3Client.getObject(request);
		byte[] byteResponse = null;
		if (s3object != null) {
			S3ObjectInputStream objectContent = s3object.getObjectContent();
			byteResponse = IOUtils.toByteArray(objectContent);
			FileUtils.writeByteArrayToFile(new File("D://UsersBucket//" + fileName), byteResponse);
			return byteResponse;
		}
		return byteResponse;
	}
}
