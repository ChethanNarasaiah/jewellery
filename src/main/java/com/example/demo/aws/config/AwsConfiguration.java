package com.example.demo.aws.config;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class AwsConfiguration {

	public AmazonS3 s3Configuration() {

		AWSCredentials credentials = new BasicAWSCredentials("AKIAJ7NWXWPZ57TBVNHA",
				"OTnzjmOHIM4B5AA7pwWSusG1hP/xowtiuT+LCqZ6");
		AmazonS3 s3client = new AmazonS3Client(credentials);
		return s3client;
	}

}
