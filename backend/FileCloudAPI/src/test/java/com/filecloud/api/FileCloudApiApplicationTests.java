package com.filecloud.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.filecloud.api.model.FileItem;
import com.filecloud.api.repository.FileItemRepository;



@SpringBootTest
class FileCloudApiApplicationTests {
	@Autowired
	private FileItemRepository fileItemRepository;
/*
	@Test
	void contextLoads() {
		assertThat(fileItemRepository).isNotNull();
	}
	
	@Test
	void addFileItem() {
		FileItem fileItem = new FileItem();
		fileItem.setId(1);
		fileItem.setFileName("testFileName01");
		fileItem.setFileType("testFileType01");
		fileItem.setFileUrl("testFileUrl01");
		fileItemRepository.addFileItem(fileItem);
		System.out.println(fileItem);
	}
*/
	@Test
	public List<FileItem> getALL(){
		return fileItemRepository.getAll();
	}
}
