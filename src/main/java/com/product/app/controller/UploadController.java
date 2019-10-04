package com.product.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.app.dto.UploadFileResponseDto;
import com.product.app.service.UploadService;

@RestController
@RequestMapping("/ing")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class UploadController {

		
	@Autowired
	private UploadService uploadService;

	@PostMapping("/upload") 
	public ResponseEntity<UploadFileResponseDto> singleFileUpload(@RequestParam("file") MultipartFile file) {

		UploadFileResponseDto message=uploadService.uploadFile(file);
		
				return new ResponseEntity<>(message,HttpStatus.OK);

	}

}
