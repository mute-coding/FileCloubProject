package com.filecloud.api.model;

public class FileItem {
	private int id;
	private String fileName;
	private String fileType;
	private String fileUrl;
	
	public FileItem() {

	}
	
    public FileItem(int id, String fileName, String fileType, String fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUrl = fileUrl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "FileItem [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileUrl=" + fileUrl + "]";
    }
	
	
}
