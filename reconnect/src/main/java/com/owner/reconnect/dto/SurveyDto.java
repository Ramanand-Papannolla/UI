package com.owner.reconnect.dto;

public class SurveyDto extends AreaDto {
	private String surveyNum;
	private DocumentInfoDto documentDto;

	public SurveyDto() {
	}

	public SurveyDto(String surveyNum) {
		this.surveyNum = surveyNum;
	}

	public String getSurveyNum() {
		return surveyNum;
	}

	public void setSurveyNum(String surveyNum) {
		this.surveyNum = surveyNum;
	}

	public DocumentInfoDto getDocumentDto() {
		return documentDto;
	}

	public void setDocumentDto(DocumentInfoDto documentDto) {
		this.documentDto = documentDto;
	}

}
