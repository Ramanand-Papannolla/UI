package com.owner.reconnect.dto;

public class VentureDtlsDto {
	private SurveyDto surveyDto;
	private VillageDto village;

	public SurveyDto getSurveyDto() {
		return surveyDto;
	}

	public VillageDto getVillage() {
		return village;
	}

	public void setSurveyDto(SurveyDto surveyDto) {
		this.surveyDto = surveyDto;
	}

	public void setVillage(VillageDto village) {
		this.village = village;
	}
}
