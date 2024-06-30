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
	@Insert("INSERT INTO FileList (FileName,FileType,FileUrl) VALUES (#{FileName},#{FileType},#{FileUrl})")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int addFileItem(FileItem fileItem);
	@Select("SELECT * FROM FileList")
	List<FileItem> getALL();
	@Delete("DELETE FROM FileList WHERE id = #{id} ")
	int deleteItem(Integer id);
	@Update("UPDATE FileList SET FileName = #{FileName} WHERE id = #{id}")
	int updateFileName(@Param("id") Integer id, @Param("FileName") String fileName);
	@Select("SELECT * FROM FileList WHERE id = #{id}")
	List<FileItem> getFileName(@Param("id") Integer id) ;

}
