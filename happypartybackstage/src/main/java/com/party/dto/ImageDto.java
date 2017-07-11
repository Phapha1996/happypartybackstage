package com.party.dto;

public class ImageDto {
	private String imageName;
	private String imageServerPath;
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageServerPath() {
		return imageServerPath;
	}
	public void setImageServerPath(String imageServerPath) {
		this.imageServerPath = imageServerPath;
	}
	public ImageDto(String imageName, String imageServerPath) {
		super();
		this.imageName = imageName;
		this.imageServerPath = imageServerPath;
	}
	
	public ImageDto(){
		super();
	}
}
