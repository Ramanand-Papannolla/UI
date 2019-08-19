package com.owner.reconnect.dto;

public class UserPlotInfoDto {
	private UserDetailsDto user;
	private long stateId;
	private long districtId;
	private long mandalId;
	private long villageId;
	private SurveyDto surveyNumber;
	private DocumentInfoDto surveyDoc;
	private PlotDto plotNumber;
	private DocumentInfoDto plotDoc;

	public UserDetailsDto getUser() {
		return user;
	}

	public long getStateId() {
		return stateId;
	}

	public long getDistrictId() {
		return districtId;
	}

	public long getMandalId() {
		return mandalId;
	}

	public long getVillageId() {
		return villageId;
	}

	public SurveyDto getSurveyNumber() {
		return surveyNumber;
	}

	public PlotDto getPlotNumber() {
		return plotNumber;
	}

	public void setUser(UserDetailsDto user) {
		this.user = user;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public void setMandalId(long mandalId) {
		this.mandalId = mandalId;
	}

	public void setVillageId(long villageId) {
		this.villageId = villageId;
	}

	public void setSurveyNumber(SurveyDto surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public void setPlotNumber(PlotDto plotNumber) {
		this.plotNumber = plotNumber;
	}

	public DocumentInfoDto getSurveyDoc() {
		return surveyDoc;
	}

	public void setSurveyDoc(DocumentInfoDto surveyDoc) {
		this.surveyDoc = surveyDoc;
	}

	public DocumentInfoDto getPlotDoc() {
		return plotDoc;
	}

	public void setPlotDoc(DocumentInfoDto plotDoc) {
		this.plotDoc = plotDoc;
	}

}
