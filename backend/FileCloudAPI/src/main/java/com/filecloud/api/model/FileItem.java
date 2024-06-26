package com.filecloud.api.model;

public class FileItem {
	private int id;
	private String FileName;
	private String FileType;
	private String FileUrl;
	
	public FileItem() {

	}
	
	public FileItem(int id, String fileName, String fileType, String FileUrl) {
		super();
		this.id = id;
		FileName = fileName;
		FileType = fileType;
		this.FileUrl = FileUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.FileUrl = fileUrl;
	}
	@Override
	public String toString() {
		return "FileItem [id=" + id + ", FileName=" + FileName + ", FileType=" + FileType + ", FileUrl=" + FileUrl
				+ "]";
	}
	
	
}
