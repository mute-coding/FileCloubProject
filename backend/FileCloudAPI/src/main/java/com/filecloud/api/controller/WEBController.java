package com.filecloud.api.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filecloud.api.model.FileItem;
import com.filecloud.api.service.FileItemService;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/fileapi")
public class WEBController {
	@Autowired
	private FileItemService fileItemService;
	 private final Path imageLocation = Paths.get("/FileCloudProject/backend/FileCloudAPI/mediafile");
	  @PostMapping("/createfile")
	  public ResponseEntity<String> addFileItem(@RequestParam("fileName") String fileName, 
												@RequestParam("fileType") String fileType, 
												@RequestParam("fileUrl") MultipartFile fileUrl) throws IOException{
		 fileItemService.createFileItem(fileName, fileType, fileUrl);
		  return  ResponseEntity.status(HttpStatus.CREATED).body("File created successfully");
	  }
	  
	  @GetMapping("/getall")
	  public ResponseEntity<List<FileItem>> getALLList(){
		  return   ResponseEntity.ok(fileItemService.getAll());
	  }
	  @DeleteMapping("/deleteitem/{id}")
	    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
	        fileItemService.deleteItem(id);
	        return ResponseEntity.ok("File deleted successfully");
	    }

	  @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateFileName(@PathVariable Integer id,
	                                                 @RequestParam("fileName") String fileName) {
	        fileItemService.updateFileName(id, fileName);
	        return ResponseEntity.ok("File name updated successfully");
	    }
	  @GetMapping("/getfileitem/{id}")
	    public ResponseEntity<FileItem> getFileItemById(@PathVariable Integer id) {
	        FileItem fileItem = fileItemService.getFileItemById(id);
	        if (fileItem != null) {
	            return ResponseEntity.ok(fileItem);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	  @GetMapping("/downloadFile/{fileResource}")
	    public ResponseEntity<?> downloadFile(@PathVariable("fileResource") String fileResource) {
	        Resource resource = null;
	        try {
	            resource = fileItemService.getFileResource(fileResource);
	        } catch (IOException e) {
	            return ResponseEntity.internalServerError().build();
	        }
	         
	        if (resource == null) {
	            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
	        }
	         
	        String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	         
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
	                .body(resource);       
	  }
	  @PostConstruct
	    public void init() {
	        System.out.println("Image location path: " + imageLocation.toAbsolutePath().toString());
	    }

	  @GetMapping("/image/{filename}")
	    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		  try {
	            Resource resource = fileItemService.getImageResource(filename);

	            if (resource != null) {
	                String contentType = Files.probeContentType(Paths.get(resource.getURI()));
	                if (contentType == null) {
	                    contentType = "application/octet-stream";
	                }

	                return ResponseEntity.ok()
	                        .contentType(MediaType.parseMediaType(contentType))
	                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
	                        .body(resource);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.badRequest().build();
	        }
	    }

}
