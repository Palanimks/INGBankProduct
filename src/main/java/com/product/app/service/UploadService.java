package com.product.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.product.app.dto.UploadFileResponseDto;

public interface UploadService {
	
	public UploadFileResponseDto uploadFile(MultipartFile file);

}
