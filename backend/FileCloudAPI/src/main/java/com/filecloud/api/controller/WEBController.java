package com.filecloud.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filecloud.api.model.FileItem;
import com.filecloud.api.service.FileItemSrevice;

@RestController
@RequestMapping("/fileapi")
public class WEBController {
	@Autowired
	private FileItemSrevice fileItemSrevice;
	  @PostMapping("/createfile")
	    public void addFileItem(@RequestParam("fileName") String fileName, 
	    						@RequestParam("fileType") String fileType, 
	    						@RequestParam("fileUrl") MultipartFile fileUrl) throws IOException {
	        fileItemSrevice.createFileItem(fileName, fileType, fileUrl);
	    }  
	  @GetMapping("/getall")
	  	public List<FileItem> getALLList(){
		  return fileItemSrevice.getALL();
	  }
	  @DeleteMapping("/deleteitem/{id}")
	  	public void deleteItem(@PathVariable Integer id) {
		  fileItemSrevice.deleteItem(id);
	  } 
	  @PutMapping("/update/{id}")
		public void updateFileName(@PathVariable Integer id,
								   @RequestParam("fileName") String fileName) {
		  fileItemSrevice.updateFileName(id,fileName);
	  }
	  @GetMapping("/getfilename/{id}")
	   	public List<FileItem> getFileName(@PathVariable Integer id){
		  return fileItemSrevice.getFileName(id);
	  }

}
