package com.owner.reconnect.dto;

public class PlotDto extends AreaDto {
	private String plotNumber;
	private DocumentInfoDto documentDto;

	public PlotDto() {
		super();
	}

	public PlotDto(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	public PlotDto(Long id, String name) {
		super(id, name);
	}

	public String getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	public DocumentInfoDto getDocumentDto() {
		return documentDto;
	}

	public void setDocumentDto(DocumentInfoDto documentDto) {
		this.documentDto = documentDto;
	}

}
