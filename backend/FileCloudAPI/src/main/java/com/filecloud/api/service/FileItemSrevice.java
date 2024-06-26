package com.filecloud.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filecloud.api.model.FileItem;
import com.filecloud.api.repository.FileItemRepository;

@Service
public class FileItemSrevice {
	@Autowired
	private FileItemRepository fileItemRepository;
	@Value("${file.upload-dir}")
	private String uploadDir;
	public void createFileItem(String fileName,String fileType,MultipartFile fileUrl)throws IOException {
		FileItem fileItem = new FileItem();
		fileItem.setFileName(fileName);
		fileItem.setFileType(fileType);
		//fileItem.setFileUrl(fileUrl);
        // 將文件上傳到指定目錄
		 String originalFileName = fileUrl.getOriginalFilename();
	     File dest = new File(uploadDir + "/" + originalFileName);
	     fileUrl.transferTo(dest);
        // 將檔案路徑存至資料庫
	     fileItem.setFileUrl(dest.getAbsolutePath());
	     int result = fileItemRepository.addFileItem(fileItem);
	     if (result != 1) {
	            throw new RuntimeException("Failed to insert file item into database.");
	        }
	}
	public List<FileItem> getALL(){
		return fileItemRepository.getALL();
	}
	public void deleteItem(Integer id) {
		fileItemRepository.deleteItem(id);
	}
	public void updateFileName(Integer id,String fileName) {
		fileItemRepository.updateFileName(id,fileName);
		
	}
	public List<FileItem> getFileName(Integer id){
		return fileItemRepository.getFileName(id);
	}

	/*
	public void fileDownload() {
		
	}
	*/
}
