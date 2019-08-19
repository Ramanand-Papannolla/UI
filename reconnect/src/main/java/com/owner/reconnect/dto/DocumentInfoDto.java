package com.owner.reconnect.dto;

import java.io.InputStream;


public class DocumentInfoDto {
	private String image;
	private String title;
	private String type;

	public DocumentInfoDto() {
		super();
	}

	public DocumentInfoDto(String image) {
		super();
		this.image = image;
	}

	public DocumentInfoDto(String image, String title, String type) {
		super();
		this.image = image;
		this.title = title;
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
