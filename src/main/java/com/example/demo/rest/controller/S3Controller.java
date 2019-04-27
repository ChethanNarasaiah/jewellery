package com.example.demo.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/s3")
@Api(value = "s3 file")
public class S3Controller {

	@Autowired
	private S3Service s3Service;

	@ApiOperation(value = "upload file", notes = "This api is used upload file in bucket", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 404, message = "NOT_FOUND") })
	@PostMapping(value = "/fileUpload", headers = "content-type=multipart/*", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam("file") MultipartFile uploadfile) throws IOException {
		return s3Service.uploadFileToSpecificBucket(uploadfile);
	}

	@ApiOperation(value = "download object", notes = "This api is used download object in bucket", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server error"),
			@ApiResponse(code = 404, message = "NOT_FOUND") })
	@GetMapping(value = "bucketname/{file}")
	public ResponseEntity<byte[]> download(@PathVariable("file") String fileName) throws IOException {
		return new ResponseEntity<byte[]>(s3Service.downloadFile(fileName), HttpStatus.OK);
	}

}
