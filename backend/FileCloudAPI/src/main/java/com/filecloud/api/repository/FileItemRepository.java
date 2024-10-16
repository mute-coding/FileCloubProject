package com.filecloud.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.filecloud.api.model.FileItem;

@Mapper
public interface FileItemRepository {
	  @Insert("INSERT INTO FileList (fileName, fileType, fileUrl) VALUES (#{fileName}, #{fileType}, #{fileUrl})")
	    @Options(useGeneratedKeys = true, keyProperty = "id")
	    int addFileItem(FileItem fileItem);

	    @Select("SELECT * FROM FileList")
	    List<FileItem> getAll();

	    @Delete("DELETE FROM FileList WHERE id = #{id}")
	    int deleteItem(@Param("id") Integer id);

	    @Update("UPDATE FileList SET fileName = #{fileName} WHERE id = #{id}")
	    int updateFileName(@Param("id") Integer id, @Param("fileName") String fileName);

	    @Select("SELECT * FROM FileList WHERE id = #{id}")
	    FileItem getFileItemById(@Param("id") Integer id);

	    @Select("SELECT * FROM FileList WHERE fileName = #{fileName}")
	    FileItem findFileItemByName(@Param("fileName") String fileName);

	
}
