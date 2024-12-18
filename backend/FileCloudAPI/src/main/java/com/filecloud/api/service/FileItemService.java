package com.filecloud.api.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filecloud.api.model.FileItem;
import com.filecloud.api.repository.FileItemRepository;


import org.springframework.util.StringUtils;

@Service
public class FileItemService {
	  private Path foundFile;
	  private final Path imageLocation = Paths.get("/FileCloudProject/backend/FileCloudAPI/mediafile");
	@Autowired
	private FileItemRepository fileItemRepository;
	public void createFileItem(String fileName,String fileType,MultipartFile fileUrl)throws IOException {
		 Path uploadDir = Paths.get("mediafile");
	        if (!Files.exists(uploadDir)) {
	            Files.createDirectories(uploadDir);
	        }
	        
	        String cleanedFileName = StringUtils.cleanPath(fileUrl.getOriginalFilename());
	        Path filePath = uploadDir.resolve(cleanedFileName);

	        try (InputStream inputStream = fileUrl.getInputStream()) {
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (Exception e) {
	            throw new IOException("Error saving: " + cleanedFileName, e);
	        }
	        String fileUrlString = filePath.toString().replace(File.separatorChar, '/');

	        FileItem fileItem = new FileItem();
	        fileItem.setFileName(cleanedFileName);
	        fileItem.setFileType(fileType);
	        fileItem.setFileUrl("/" + fileUrlString);

	        int result = fileItemRepository.addFileItem(fileItem);
	        if (result != 1) {
	            throw new RuntimeException("Failed to insert file item into database.");
	        }
		}


	 public List<FileItem> getAll() {
	        return fileItemRepository.getAll();
	    }

	 public void deleteItem(Integer id) {
	        fileItemRepository.deleteItem(id);
	    }

	 public void updateFileName(Integer id, String fileName) {
	        fileItemRepository.updateFileName(id, fileName);
	    }

	 public FileItem getFileItemById(Integer id) {
	        return fileItemRepository.getFileItemById(id);
	    }
	public Resource getFileResource(String fileResource)throws IOException{
		Path path = Paths.get("mediafile");
		 Files.list(path).forEach(file -> {
	            if (file.getFileName().toString().startsWith(fileResource)) {
	                foundFile = file;
	                return;
	            }
	        });
	 
	        if (foundFile != null) {
	            return new UrlResource(foundFile.toUri());
	        }
		return null;
	}
	 public Resource getImageResource(String filename) throws IOException {
	        Path filePath = imageLocation.resolve(filename).normalize();
	        String normalizedPath = filePath.toString().replace("\\", "/");
	        System.out.println("Requested file path: " + normalizedPath);

	        Resource resource = new UrlResource("file:" + normalizedPath);

	        if (resource.exists() && resource.isReadable()) {
	            return resource;
	        } else {
	            System.out.println("File not found or not readable: " + normalizedPath);
	            return null;
	        }
	    }

}
